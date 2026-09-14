package org.firstinspires.ftc.teamcode.SubSystems;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class DriveTrain extends SubsystemBase {
    Robot robot = Robot.getInstance();

    private final Follower follower;
    public static Pose startingPose;
    private double slowMultiplier = 0.5;
    private final Gamepad gp1 = robot.Systems.gamepad1;

    public DriveTrain() {
        super();

        HardwareMap hw = robot.Systems.hw;
        follower = Constants.createFollower(hw);
        follower.setStartingPose(startingPose == null ? new Pose() : startingPose);
        follower.update();
        follower.startTeleopDrive();
    }

    public void drive() {
        follower.update();

        follower.setTeleOpDrive(
                -gp1.left_stick_y * slowMultiplier,
                -gp1.left_stick_x * slowMultiplier,
                -gp1.right_stick_x * slowMultiplier,
                false // robot centric
        );

        if (gp1.leftBumperWasPressed()) {
            slowMultiplier += 0.25;
        }

        if (gp1.rightBumperWasPressed()) {
            slowMultiplier -= 0.25;
        }
    }
}