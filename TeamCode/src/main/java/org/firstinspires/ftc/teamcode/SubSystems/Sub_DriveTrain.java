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
    private final Follower follower;

    private final Gamepad gamepad1;

    public Sub_DriveTrain(HardwareMap hardwareMap, Gamepad gamepad1) {
        this.gamepad1 = gamepad1;

        follower = Constants.create(hardwareMap);
        follower.setPose(Pose.zero());
    }

    public void drive() {
        if (!follower.isBusy()) {
            DrivePowers drivePowers = getDrivePowers();
            follower.manual(drivePowers);
        }

        follower.update();
    }

    private DrivePowers getDrivePowers() {
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

    public void snippetGoHome() {
        Pose currentPose = follower.pose();
        Path toHome = Paths.line(currentPose, Pose.zero()).linear(currentPose, Pose.zero());
        follower.follow(toHome);
    }
}
