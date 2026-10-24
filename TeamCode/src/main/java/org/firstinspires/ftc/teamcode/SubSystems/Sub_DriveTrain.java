package org.firstinspires.ftc.teamcode.SubSystems;

import com.pedropathing.api.Paths;
import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.pedro.Constants;

public class Sub_DriveTrain {
    private static Gamepad gamepad1;
    private static HardwareMap hardwareMap;

    public Sub_DriveTrain(HardwareMap hardwareMap, Gamepad gamepad1) {
        Sub_DriveTrain.hardwareMap = hardwareMap;
        Sub_DriveTrain.gamepad1 = gamepad1;
    }

    public static Follower getFollower() {
        Follower follower = Constants.create(hardwareMap);
        follower.setPose(Pose.zero());

        return follower;
    }

    public static void drive(Follower follower) {
        if (!follower.isBusy()) {
            DrivePowers drivePowers = getDrivePowers(follower);
            follower.manual(drivePowers);
            follower.update();
        }
    }

    private static DrivePowers getDrivePowers(Follower follower) {
        float forward = -gamepad1.left_stick_y;
        float strafe = -gamepad1.left_stick_x;
        float turn = -gamepad1.right_stick_x;

        if (gamepad1.xWasPressed()) {
            follower.setPose(Pose.zero());
        }

        DrivePowers drivePowers;

        if (gamepad1.right_trigger == 0) {
            drivePowers = ManualDrive.fieldCentric(
                    forward,
                    strafe,
                    turn,
                    follower.pose().heading()
            );
        } else {
            drivePowers = new DrivePowers(
                    forward,
                    strafe,
                    turn
            );
        }

        return drivePowers;
    }

    public static void goHome(Follower follower) {
        Pose followerPose = follower.pose();
        Path path = Paths.line(followerPose, Pose.zero()).linear(followerPose, Pose.zero());
        follower.follow(path);
    }
}
