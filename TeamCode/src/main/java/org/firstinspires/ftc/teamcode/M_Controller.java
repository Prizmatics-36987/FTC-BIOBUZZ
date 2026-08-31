package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@TeleOp(name="M_Controller", group="Linear OpMode")
public class M_Controller extends OpMode {
    private Follower follower;
    public static Pose startingPose;
    private double slowMultiplier = 0.5;

    @Override
    public void init() {
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startingPose == null ? new Pose() : startingPose);
        follower.update();
    }

    @Override
    public void start() {
        follower.startTeleopDrive();
    }

    @Override
    public void loop() {
        follower.update();

        follower.setTeleOpDrive(
                -gamepad1.left_stick_y * slowMultiplier,
                -gamepad1.left_stick_x * slowMultiplier,
                -gamepad1.right_stick_x * slowMultiplier,
                false // Robot Centric
        );

        if (gamepad1.leftBumperWasPressed()) {
            slowMultiplier += 0.25;
        }

        if (gamepad1.rightBumperWasPressed()) {
            slowMultiplier -= 0.25;
        }

        telemetry.addData("Multiplier", slowMultiplier);
    }
}