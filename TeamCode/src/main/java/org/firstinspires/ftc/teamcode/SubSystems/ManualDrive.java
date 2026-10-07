package org.firstinspires.ftc.teamcode.SubSystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class ManualDrive extends SubsystemBase {
    private final Follower follower;
    private double movementMultiplier = 0.5;
    Intake intake;

    public ManualDrive(HardwareMap hardwareMap) {
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose());
        follower.update();
        follower.startTeleopDrive();

        intake = new Intake(hardwareMap);
    }

    public void drive(boolean fieldCentric) {
        follower.update();

        follower.setTeleOpDrive(
                -gamepad1.left_stick_y * movementMultiplier,
                -gamepad1.left_stick_x * movementMultiplier,
                -gamepad1.right_stick_x * movementMultiplier,
                !fieldCentric
        );

        // Movement Speed
        if (gamepad1.leftBumperWasPressed()) {
            movementMultiplier += 0.25;
        } else if (gamepad1.rightBumperWasPressed()) {
            movementMultiplier -= 0.25;
        }

        // Intake
        if (gamepad1.dpad_up) {
            intake.setPower(1);
        } else if (gamepad1.dpad_down) {
            intake.setPower(-1);
        }
    }
}