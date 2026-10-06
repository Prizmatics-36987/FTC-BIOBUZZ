package org.firstinspires.ftc.teamcode.TeleOps.Autonomous;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

import java.util.concurrent.TimeUnit;

@Autonomous(name = "AutoPath", group = "Autonomous")
public class A_FullAuto extends OpMode {
    private Follower follower;
    private PathChain toFirstShoot, toFlower, toShoot;

    private State pathState;
    private Side shootSide;

    private final Pose startPose = new Pose(35, 12, Math.toRadians(90));
    private final Pose secPose = new Pose(58, 35, Math.toRadians(90));
    private final Pose thirdPose = new Pose(10, 50, Math.toRadians(180));
    private final Pose forthPose = new Pose(58, 105, Math.toRadians(270));
    private final Pose forthPoseCtrl1 = new Pose(10, 110, Math.toRadians(0));

    @Override
    public void init() {
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startPose);
        buildPaths();
    }

    @Override
    public void start() {
        pathState = State.READY;
    }

    @Override
    public void loop() {
        follower.update();
        autonomousPathUpdate();
        telemetry.addData("Path State", pathState);
        telemetry.addData("Pose", follower.getPose());
        telemetry.update();
    }

    private void buildPaths() {
        toFirstShoot = follower.pathBuilder()
                .addPath(new BezierLine(startPose, secPose))
                .setConstantHeadingInterpolation(secPose.getHeading())
                .build();

        toFlower = follower.pathBuilder()
                .addPath(new BezierLine(secPose, thirdPose))
                .setLinearHeadingInterpolation(secPose.getHeading(), thirdPose.getHeading())
                .build();

        toShoot = follower.pathBuilder()
                .addPath(new BezierCurve(thirdPose, forthPoseCtrl1, forthPose))
                .setLinearHeadingInterpolation(thirdPose.getHeading(), forthPose.getHeading())
                .build();
    }

    private void autonomousPathUpdate() {
        switch (pathState) {
            case READY:
                follower.followPath(toFirstShoot);

                shootSide = Side.RIGHT;
                pathState = State.SHOOT;
            case SHOOT:
                if (!follower.isBusy()) {
//                    outtake.shoot();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    switch (shootSide) {
                        case LEFT:
                            pathState = State.DONE;
                        case RIGHT:
                            follower.followPath(toFlower);
                            pathState = State.FLOWER;
                    }
                }
            case FLOWER:
                if (!follower.isBusy()) {
//                    intake.setPower(1)
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    follower.followPath(toShoot);

                    shootSide = Side.LEFT;
                    pathState = State.SHOOT;
                }
            case DONE:
        }
    }
}