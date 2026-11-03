package org.firstinspires.ftc.teamcode.OpMode.Autonomous;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.skeletonarmy.marrow.weaver.PathConfig;
import com.skeletonarmy.marrow.weaver.PathResult;
import com.skeletonarmy.marrow.weaver.PedroPathingConverter;
import com.skeletonarmy.marrow.weaver.Weaver;
import com.skeletonarmy.marrow.zones.CircleZone;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous(name="A_TestMarrow", group = "Linear OpMode")
public class A_TestMarrow extends OpMode {
    public Follower follower;

    Pose start;
    Pose obstacle;
    Pose pollen;
    Pose pollen2;

    @Override
    public void init() {
        Weaver.setConfig(new PathConfig()
                .intakeWidth(5)
                .robotWidth(16.5)
                .robotHeight(14)
        );

        follower = Constants.create(hardwareMap);

        PoseFactory poseFactory = PoseFactory.degrees();

        start = poseFactory.of(10, 10, 90);
        obstacle = poseFactory.of(23, 45, 90);
        pollen = poseFactory.of(35, 70, 90);
        pollen2 = poseFactory.of(52, 35, 90);

        follower.setPose(start);

        PathResult path =  Weaver.builder()
                .start(PedroPathingConverter.toPoint(start))
                .addObstacle(new CircleZone(PedroPathingConverter.toPoint(obstacle), 5))
                .addTarget(PedroPathingConverter.toPoint(pollen))
                .addTarget(PedroPathingConverter.toPoint(pollen2))
                .ordered()
                .build();

        follower.follow(PedroPathingConverter.toPath(path));
    }

    @Override
    public void loop() {
        Pose currentPose = follower.pose();

        if (gamepad1.touchpadWasPressed()) {
            goHome(currentPose);
        }

        telemetry.addData("Pose: ", currentPose);
        telemetry.update();
    }

    private void goHome(Pose currentPose) {
        Path toHome = Paths.line(currentPose, start).linear(currentPose, start);
        follower.follow(toHome);
    }
}
