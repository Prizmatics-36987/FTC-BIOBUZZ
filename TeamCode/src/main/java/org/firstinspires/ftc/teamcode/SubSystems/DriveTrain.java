package org.firstinspires.ftc.teamcode.SubSystems;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class DriveTrain extends SubsystemBase {
    static Robot robot = Robot.getInstance();

    private static Follower follower = null;
    public static Pose startingPose;
    private static double slowMultiplier = 0.5;
    private static final Gamepad gp1 = robot.Systems.gamepad1;

    public DriveTrain() {
        super();

        HardwareMap hw = robot.Systems.hw;
        follower = Constants.createFollower(hw);
        follower.setStartingPose(startingPose == null ? new Pose() : startingPose);
        follower.update();
        follower.startTeleopDrive();
    }

    public static void drive() {
        follower.update();

        follower.setTeleOpDrive(
                -gp1.left_stick_y * slowMultiplier,
                -gp1.left_stick_x * slowMultiplier,
                -gp1.right_stick_x * slowMultiplier,
                false // robot centric
        );

        // Movement Speed
        if (gp1.leftBumperWasPressed()) {
            slowMultiplier += 0.25;
        } else if (gp1.rightBumperWasPressed()) {
            slowMultiplier -= 0.25;
        }

        // Intake
        if (gp1.dpad_up) {
            Intake.activate(1);
        } else if (gp1.dpad_down) {
            Intake.activate(-1);
        }
    }
}