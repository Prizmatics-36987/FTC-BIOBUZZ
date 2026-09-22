package org.firstinspires.ftc.teamcode.TeleOps;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.SubSystems.AprilTag;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagSingleDetection;

@TeleOp(name="AprilTagTest", group="Linear OpMode")
public class M_AprilTagTest extends OpMode {
    private final Gamepad gp1 = gamepad1;

    AprilTag april;

    @Override
    public void init() {
        april = new AprilTag();
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void loop() {
        telemetry.update();

        if (gp1.bWasPressed()) {
            AprilTagDetection id20 = april.getByID(20);
            april.displayDetectionTelemetry((AprilTagSingleDetection) id20);
        }
    }
}
