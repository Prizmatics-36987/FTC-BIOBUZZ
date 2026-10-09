package org.firstinspires.ftc.teamcode.OpMode.Autonomous;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.SubSystems.Sub_Intake;
import org.firstinspires.ftc.teamcode.SubSystems.Sub_Outtake;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous(name="A_Triangle", group="Linear OpMode")
public class A_Triangle extends OpMode {
    public static Follower follower;
    private PathChain path;

    private final Pose startPose = new Pose(0, 0, Math.toRadians(90));
    private final Pose topPose = new Pose(40, 40, Math.toRadians(180));
    private final Pose downPose = new Pose(40, 10, Math.toRadians(0));

    Sub_Intake intake = new Sub_Intake(hardwareMap);
    Sub_Outtake outtake = new Sub_Outtake(hardwareMap);

    @Override
    public void init() {
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startPose);

        path = follower.pathBuilder()
                .addPath(new BezierLine(startPose, topPose))
                .setLinearHeadingInterpolation(startPose.getHeading(), topPose.getHeading())
                .addPath(new BezierLine(topPose, downPose))
                .setTangentHeadingInterpolation()
                .addPath(new BezierLine(downPose, startPose))
                .setConstantHeadingInterpolation(startPose.getHeading())
                .build();
    }

    @Override
    public void start() {
        intake.setPower(1);
        outtake.setPower(1);
    }

    @Override
    public void loop() {
        follower.update();

        if (!follower.isBusy()) {
            follower.followPath(path, true);
        }
    }
}
