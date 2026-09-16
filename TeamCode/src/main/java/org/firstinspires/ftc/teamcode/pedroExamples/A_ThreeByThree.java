package org.firstinspires.ftc.teamcode.pedroExamples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

@Autonomous(name = "A_ThreeByThree", group = "Autonomous")
public class A_ThreeByThree extends OpMode {
    private Follower follower;
    private PathChain path1, path2;
    private State pathState;
    private final ElapsedTime actionTimer = new ElapsedTime();

    //poses
    private final Pose startPose = new Pose(8, 8, Math.toRadians(90));
    private final Pose midPose   = new Pose(50, 50, Math.toRadians(90));
    private final Pose endPose   = new Pose(8, 8, Math.toRadians(90));

    @Override
    public void init() {
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startPose);
        buildPaths();
    }

    @Override
    public void start() {
        setPathState(State.READY);
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
        path1 = follower.pathBuilder()
                .addPath(new BezierLine(startPose, midPose))
                .setLinearHeadingInterpolation(startPose.getHeading(), midPose.getHeading())
                .build();
        path2 = follower.pathBuilder()
                .addPath(new BezierLine(midPose, endPose))
                .setLinearHeadingInterpolation(midPose.getHeading(), endPose.getHeading())
                .build();
    }

    private void autonomousPathUpdate() {
        switch (pathState) {
            case READY: // start first path
                follower.followPath(path1);
                setPathState(State.SHOOT);
                break;
            case SHOOT: // do thing
                if (!follower.isBusy()) {
                    startAction();
                    setPathState(State.BACK);
                }
                break;
            case BACK: // timer to let it run
                if (actionTimer.seconds() > 1.5) {
                    stopAction();
                    setPathState(State.DONE);
                }
                break;
            case DONE: // start second path
                follower.followPath(path2);
                setPathState(State.FINISH);
                break;
            case FINISH:
                // brake
                break;
        }
    }

    private void setPathState(State state) {
        pathState = state;
        actionTimer.reset();
    }

    private void startAction() {
    }

    private void stopAction() {
    }
}