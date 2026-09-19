package org.firstinspires.ftc.teamcode.SubSystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import android.annotation.SuppressLint;
import android.util.Size;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.ArrayList;
import java.util.List;

public class AprilTag {
    Robot robot = Robot.getInstance();

    private static AprilTagProcessor aprilTag;
    private static VisionPortal visionPortal;

    static List<AprilTagDetection> currentDetections = new ArrayList<>();

    public AprilTag() {
        aprilTag = new AprilTagProcessor.Builder()
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setOutputUnits(DistanceUnit.CM, AngleUnit.DEGREES)
                //.setTagLibrary(AprilTagGameDatabase.getCenterStageTagLibrary())
                //.setLensIntrinsics(578.272, 578.272, 402.145, 221.506)
                // ... these parameters are fx, fy, cx, cy.
                .build();

        VisionPortal.Builder builder = new VisionPortal.Builder();
        builder.setCamera(robot.hw.get(WebcamName.class, "Webcam 1"));
        builder.setCameraResolution(new Size(640, 480));
        builder.addProcessor(aprilTag);

        visionPortal = builder.build();
        visionPortal.setProcessorEnabled(aprilTag, true);
    }

    @SuppressLint("DefaultLocale")
    public static void displayDetectionTelemetry(AprilTagDetection detectedID) {
        if (detectedID == null) {return;}
        if (detectedID.metadata != null) {
            telemetry.addLine(String.format("\n==== (ID %d) %s", detectedID.id, detectedID.metadata.name));
            telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (cm)", detectedID.ftcPose.x, detectedID.ftcPose.y, detectedID.ftcPose.z));
            telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detectedID.ftcPose.pitch, detectedID.ftcPose.roll, detectedID.ftcPose.yaw));
            telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f  (cm, deg, deg)", detectedID.ftcPose.range, detectedID.ftcPose.bearing, detectedID.ftcPose.elevation));
        } else {
            telemetry.addLine(String.format("\n==== (ID %d) Unknown", detectedID.id));
            telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detectedID.center.x, detectedID.center.y));
        }
    }

    public static AprilTagDetection getByID(int ID) {
        update();

        for (AprilTagDetection det : currentDetections) {
            if (det.id == ID) {
                return det;
            }
        }

        return null;
    }

    public static void stop() {
        if (visionPortal != null) {
            visionPortal.close();
        }
    }

    private static void update() {
        currentDetections = aprilTag.getDetections();
    }
}
