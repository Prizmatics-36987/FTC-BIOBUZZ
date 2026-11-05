package org.firstinspires.ftc.teamcode.OpMode.TeleOp;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.SubSystems.Sub_DriveTrain;
import org.firstinspires.ftc.teamcode.pedro.Constants;

@TeleOp(name="M_Flower", group="Linear OpMode")
public class M_Flower extends OpMode {
    Follower follower;

    Sub_DriveTrain driveTrain;

    PoseFactory poseFactory = PoseFactory.degrees();
    Pose flower1 = poseFactory.of(5, 20, 180);
    Pose currentPose;

    @Override
    public void init() {
        follower = Constants.create(hardwareMap);
        driveTrain = new Sub_DriveTrain(hardwareMap, gamepad1);
    }

    @Override
    public void loop() {
        currentPose = follower.pose();

        driveTrain.drive();

        if (gamepad1.touchpadWasPressed()) {
            driveTrain.snippetGoHome();
        }

        if (gamepad1.yWasPressed() && !follower.isBusy()) {
            goFlower(currentPose);
        }
    }

    private void goFlower(Pose currentPose) {
        Path toFlower = Paths.line(currentPose, flower1).linear(currentPose, flower1);
        follower.follow(toFlower);
        telemetry.addLine("driving");
    }
}
