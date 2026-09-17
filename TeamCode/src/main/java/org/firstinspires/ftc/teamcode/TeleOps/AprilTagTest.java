package org.firstinspires.ftc.teamcode.TeleOps;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.SubSystems.AprilTag;
import org.firstinspires.ftc.teamcode.Utils.InitMethods;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

@TeleOp(name="AprilTagTest", group="Linear OpMode")
public class AprilTagTest extends OpMode {
    private final Gamepad gp1 = gamepad1;

    @Override
    public void init() {
        InitMethods.initAprilTagTest();
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void loop() {
        telemetry.update();

        if (gp1.bWasPressed()) {
            AprilTagDetection id20 = AprilTag.getByID(20);
            AprilTag.displayDetectionTelemetry(id20);
        }
    }
}
