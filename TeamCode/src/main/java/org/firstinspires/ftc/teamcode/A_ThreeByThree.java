package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.follower.Follower;
import com.pedropathing.paths.PathChain;
import com.pedropathing.geometry.Pose;

@Autonomous(name = "A_ThreeByThree", group = "Autonomous")
public class A_ThreeByThree extends OpMode {
    public Follower follower; // Pedro Pathing follower instance
    private String pathState; // Current autonomous path state (state machine)
    private Paths paths; // Paths defined in the Paths class

    @Override
    public void init() {
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(8, 8, Math.toRadians(90)));

        paths = new Paths(follower); // Build paths
        pathState = "READY";
    }

    @Override
    public void loop() {
        follower.update(); // Update Pedro Pathing

        pathState = autonomousPathUpdate(); // Update autonomous state machine

        telemetry.addData("pathState", pathState);
        telemetry.update();
    }

    public static class Paths {
        public PathChain ShootPath;
        public PathChain DonePath;

        public Paths(Follower follower) {
            ShootPath = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(8.000, 8.000),
                                    new Pose(50.000, 50.000)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(180))
                    .build();

            DonePath = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(50.000, 50.000),
                                    new Pose(30.000, 30.000)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(90))
                    .build();

        }
    }

    public String autonomousPathUpdate() {
            switch (pathState) {
                case "READY":
                    if (!follower.isBusy()) {
                        follower.followPath(paths.ShootPath);
                        return "SHOOT";
                    }
                    break;
                case "SHOOT":
                    if (!follower.isBusy()) {
//                        TimeUnit.MILLISECONDS.sleep(500);
                        return "BACK";
                    }
                    break;
                case "BACK":
                    if (!follower.isBusy()) {
                        follower.followPath(paths.DonePath);
                        return "DONE";
                    }
                    break;
                case "DONE":
                    return "DONE";
            }

        return "READY";
    }
}