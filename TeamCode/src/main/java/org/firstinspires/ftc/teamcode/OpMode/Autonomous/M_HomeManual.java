package org.firstinspires.ftc.teamcode.OpMode.Autonomous;

import com.pedropathing.api.Paths;
import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@TeleOp(name="M_HomeManual", group="Linear OpMode")
public class M_HomeManual extends OpMode {
    private Follower follower;
    private boolean manualControl;

    @Override
    public void init() {
        follower = Constants.create(hardwareMap);
        follower.setPose(Pose.zero());
    }

    @Override
    public void loop() {
        if (!follower.isBusy()) {
            DrivePowers drivePowers = getDrivePowers();
            follower.manual(drivePowers);

            if (gamepad1.touchpadWasPressed()) {
                manualControl = false;

                Pose followerPose = follower.pose();
                Path path = Paths.line(followerPose, Pose.zero()).linear(followerPose, Pose.zero());
                follower.follow(path);
            }
        }

        else {
            manualControl = true;
        }



        follower.update();
    }


    private DrivePowers getDrivePowers() {
            if (!manualControl) {
                return DrivePowers.zero();
            }

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
            }

            else {
                drivePowers = new DrivePowers(
                        forward,
                        strafe,
                        turn
                );
            }
            return drivePowers;
        }
}
