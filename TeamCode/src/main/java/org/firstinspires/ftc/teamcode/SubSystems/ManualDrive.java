package org.firstinspires.ftc.teamcode.SubSystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class ManualDrive {
    private static Follower follower;
    private static double movementMultiplier = 0.5;
    private static final Gamepad gp1 = gamepad1;

    public ManualDrive() {
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose());
        follower.update();
        follower.startTeleopDrive();
    }

    public static void drive(boolean fieldCentric) {
        follower.update();

        follower.setTeleOpDrive(
                -gp1.left_stick_y * movementMultiplier,
                -gp1.left_stick_x * movementMultiplier,
                -gp1.right_stick_x * movementMultiplier,
                !fieldCentric
        );

        // Movement Speed
        if (gp1.leftBumperWasPressed()) {
            movementMultiplier += 0.25;
        } else if (gp1.rightBumperWasPressed()) {
            movementMultiplier -= 0.25;
        }

        // Intake
        if (gp1.dpad_up) {
            Intake.activate(1);
        } else if (gp1.dpad_down) {
            Intake.activate(-1);
        }
    }
}