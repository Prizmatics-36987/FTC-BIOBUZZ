package org.firstinspires.ftc.teamcode.SubSystems;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Size;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class Sub_Vision implements VisionProcessor {

    // Note: final here doesn't make the variable unchangeable, but only the reference (so it cannot
    // be reassigned).

    // The camera's image in HSV.
    private final Mat hsv = new Mat();

    // A mask representing the purple pixels.
    private final Mat purpleMask = new Mat();

    // Required by findContours and not important.
    private final Mat hierarchy = new Mat();

    // A 3x3 rectangle used for cleaning the mask up.
    private final Mat kernel = Imgproc.getStructuringElement(
            Imgproc.MORPH_RECT,
            new org.opencv.core.Size(3, 3)
    );


    // Outlines of detected shapes.
    private final List<MatOfPoint> contours = new ArrayList<>();

    // Boxes around the detected shapes.
    private final List<Rect> boxes = new ArrayList<>();

    // Stores the largest box's center.

    private volatile Point largestCenter;

    // Paint settings

    private final Paint boxPaint = new Paint();
    private final Paint textPaint = new Paint();

    public Sub_Vision() {
        boxPaint.setColor(Color.MAGENTA);
        boxPaint.setStyle(Paint.Style.STROKE);
        boxPaint.setStrokeWidth(3);

        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(24);
    }

    // Required but not needed
    @Override
    public void init(int width, int height, CameraCalibration calibration) {}

    // Runs once per frame
    @Override
    public Object processFrame(Mat frame, long captureTimeNanos) {
        //Set the points for warping.
        MatOfPoint2f screenPoints = new MatOfPoint2f(
                new Point(123, 111),
                new Point(555, 116),
                new Point(482, 46),
                new Point(164, 38)
        );

        MatOfPoint2f destinationPoints = new MatOfPoint2f(
                new Point(123, 462),
                new Point(555, 462),
                new Point(555, 30),
                new Point(123, 30)//432
        );

        Mat warpingMatrix = Imgproc.getPerspectiveTransform(screenPoints, destinationPoints);

        Mat warpedFrame = new Mat();

        // Warp the perspective and save it back into frame
        Imgproc.warpPerspective(
                frame,
                warpedFrame,
                warpingMatrix,
                new org.opencv.core.Size(640*2, 480*2)
        );

        // Change color scheme to hsv
        Imgproc.cvtColor(warpedFrame, warpedFrame, Imgproc.COLOR_RGB2HSV);

        // Checks which pixels are in the color range (purple), and saves them into the mask (black
        // if purple, white if not)
        Core.inRange(hsv, new Scalar(125, 100, 50),
                new Scalar(155, 255, 255), purpleMask);

        // Cleans the mask from noise and fills tiny holes not detected.
        Imgproc.morphologyEx(purpleMask, purpleMask, Imgproc.MORPH_OPEN, kernel);
        Imgproc.morphologyEx(purpleMask, purpleMask, Imgproc.MORPH_CLOSE, kernel);

        for (MatOfPoint contour : contours) {
            contour.release();
        }

        // Cleans old results
        contours.clear();
        boxes.clear();
        largestCenter = null;

        // Finds the object outlines and saves it into contours. RETR_EXTERNAL means to save only
        // the outlines and not the insides. CHAIN_APPROX_SIMPLE compresses straight parts of the
        // outline.
        Imgproc.findContours(
                purpleMask, contours, hierarchy,
                Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE
        );

        double largestArea = 100;

        // for each object detected
        for (MatOfPoint contour : contours) {
            // find the area (in pixels)
            double area = Imgproc.contourArea(contour);

            // ignores everything under 100 pixels
            if (area < 100) {
                continue;
            }

            // finds the box surrounding each object
            Rect box = Imgproc.boundingRect(contour);
            boxes.add(box);

            // finds the largest object and saves its center
            if (area > largestArea) {
                largestArea = area;
                largestCenter = new Point(
                        box.x + box.width / 2.0,
                        box.y + box.height / 2.0
                );
            }
        }

        return null;
    }

    // Preview
    @Override
    public void onDrawFrame(
            Canvas canvas,
            int onscreenWidth,
            int onscreenHeight,
            float scaleBmpPxToCanvasPx,
            float scaleCanvasDensity,
            Object userContext
    ) {

        // Finds the correct ratio between the processed image and the preview side
        float scaleX = onscreenWidth / 640.0f;
        float scaleY = onscreenHeight / 480.0f;

        // for each box, draw a rectangle
        synchronized (boxes) {
            for (Rect box : boxes) {
                // draw a rectangle with boxPaint
                canvas.drawRect(
                        box.x * scaleX,
                        box.y * scaleY,
                        (box.x + box.width) * scaleX,
                        (box.y + box.height) * scaleY,
                        boxPaint
                );
            }
        }

        Point center = largestCenter;

        // draw text "TARGET DETECTED" on the largest center
        if (center != null) {
            canvas.drawText(
                    "TARGET DETECTED",
                    (float) center.x * scaleX,
                    (float) center.y * scaleY,
                    textPaint
            );
        }
    }

    public Point getLargestCenter() {
        Point center = largestCenter;
        return center == null ? null : new Point(center.x, center.y);
    }

    public VisionPortal getVisionPortal(HardwareMap hardwareMap) {
        return new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "webcam"))
                .setCameraResolution(new Size(640, 480))
                .addProcessor(this)
                .build();
    }
}
