
package org.firstinspires.ftc.teamcode.OpMode.Auto;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Size;

import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.teamcode.SubSystems.Sub_Intake;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

@Autonomous(name = "A_balolo", group = "Linear OpMode")
public class A_balolo extends OpMode {
    private Follower follower;
    private VisionPortal visionPortal;
    private RedProcessor pipe;

    private Sub_Intake intake1;

    @Override
    public void init() {
        follower = Constants.create(hardwareMap);
        pipe = new RedProcessor();
        intake1 = new Sub_Intake(hardwareMap);


        visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .setCameraResolution(new Size(640, 480))
                .addProcessor(pipe)
                .build();
    }

    @Override
    public void loop() {
        Point target = pipe.getLargestCenter();

        if (target != null) {
            telemetry.addData("Red object X", target.x);
            telemetry.addData("Red object Y", target.y);
            telemetry.addData("Horizontal error", target.x - 320.0);
            double Tspeed = ((target.x -320.0) / 320) * 0.75;
            double speed = ((target.y - 480) / 240) * 0.75;
            intake1.setPower(1.0);
            
            follower.manual(0.5, 0, -Tspeed);
        } else {
            telemetry.addLine("No red object detected");
            follower.stop();
        }

        follower.update();
        telemetry.update();
    }

    @Override
    public void stop() {
        if (visionPortal != null) {
            visionPortal.close();
        }
    }
}

class RedProcessor implements VisionProcessor {
    private final Mat hsv = new Mat();
    private final Mat redLow = new Mat();
    private final Mat redHigh = new Mat();
    private final Mat mask = new Mat();
    private final Mat hierarchy = new Mat();
    private final Mat kernel = Imgproc.getStructuringElement(
            Imgproc.MORPH_RECT,
            new org.opencv.core.Size(3, 3)
    );

    private final List<MatOfPoint> contours = new ArrayList<>();
    private final List<Rect> boxes = new ArrayList<>();

    private volatile Point largestCenter;

    private final Paint boxPaint = new Paint();
    private final Paint textPaint = new Paint();

    RedProcessor() {
        boxPaint.setColor(Color.MAGENTA);
        boxPaint.setStyle(Paint.Style.STROKE);
        boxPaint.setStrokeWidth(3);

        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(24);
    }

    @Override
    public void init(int width, int height, CameraCalibration calibration) {
    }

    @Override
    public Object processFrame(Mat frame, long captureTimeNanos) {
        Imgproc.cvtColor(frame, hsv, Imgproc.COLOR_RGB2HSV);

        Core.inRange(hsv, new Scalar(125, 100, 50),
                new Scalar(155, 255, 255), mask);

        Imgproc.morphologyEx(mask, mask, Imgproc.MORPH_OPEN, kernel);
        Imgproc.morphologyEx(mask, mask, Imgproc.MORPH_CLOSE, kernel);

        for (MatOfPoint contour : contours) {
            contour.release();
        }

        contours.clear();
        boxes.clear();
        largestCenter = null;

        Imgproc.findContours(
                mask, contours, hierarchy,
                Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE
        );

        double largestArea = 100;

        for (MatOfPoint contour : contours) {
            double area = Imgproc.contourArea(contour);

            if (area < 100) {
                continue;
            }

            Rect box = Imgproc.boundingRect(contour);
            boxes.add(box);

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

    @Override
    public void onDrawFrame(
            Canvas canvas,
            int onscreenWidth,
            int onscreenHeight,
            float scaleBmpPxToCanvasPx,
            float scaleCanvasDensity,
            Object userContext
    ) {
        float scaleX = onscreenWidth / 640.0f;
        float scaleY = onscreenHeight / 480.0f;

        synchronized (boxes) {
            for (Rect box : boxes) {
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

        if (center != null) {
            canvas.drawText(
                    "TARGET DETECTED",
                    (float) center.x * scaleX,
                    (float) center.y * scaleY,
                    textPaint
            );
        }
    }

    Point getLargestCenter() {
        Point center = largestCenter;
        return center == null ? null : new Point(center.x, center.y);
    }
}
