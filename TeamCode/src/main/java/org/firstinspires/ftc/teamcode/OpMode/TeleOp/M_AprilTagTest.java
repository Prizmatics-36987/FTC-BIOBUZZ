package org.firstinspires.ftc.teamcode.OpMode.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.SubSystems.Sub_AprilTag;
import org.firstinspires.ftc.vision.apriltag.AprilTagClusterDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagSingleDetection;

import java.util.ArrayList;

@TeleOp(name="M_AprilTagTest", group="Linear OpMode")
public class M_AprilTagTest extends OpMode {
    Sub_AprilTag aprilTag;

    @Override
    public void init() {
        aprilTag = new Sub_AprilTag(hardwareMap);
    }

    @Override
    public void loop() {
        ArrayList<AprilTagDetection> detections = aprilTag.getALlDetections();
        if (detections.isEmpty()) {
            telemetry.addLine("No AprilTags!");
        }

        for (AprilTagDetection detection : detections) {
            if (detection instanceof AprilTagSingleDetection) {
                telemetry.addData("Single tag detected, id: ", ((AprilTagSingleDetection) detection).id);
            }

            if (detection instanceof AprilTagClusterDetection) {
                telemetry.addData("Tag cluster, name: ", ((AprilTagClusterDetection) detection).metadata.name);
            }
        }

        telemetry.update();
    }
}
