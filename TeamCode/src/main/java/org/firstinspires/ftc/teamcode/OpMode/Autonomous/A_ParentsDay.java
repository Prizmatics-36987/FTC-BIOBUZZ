package org.firstinspires.ftc.teamcode.OpMode.Autonomous;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous(name="A_ParentsDay", group="Linear OpMode")
public class A_ParentsDay extends OpMode {
    private Follower follower;

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(130, 10, 90);
    private final Pose point1 = poseFactory.of(130, 60, 90);
    private final Pose point2 = poseFactory.of(60, 60, 180);
    private final Pose point3 = poseFactory.of(60, 106, 0);
    private final Pose point4 = poseFactory.of(105, 106, 0);
    private final Pose point5 = poseFactory.of(105, 120, 90);

    public final Path paths = Paths.path(
            Paths.line(start, point1).linear(start, point1),
            Paths.line(point1, point2).linear(point1, point2),
            Paths.line(point2, point3).linear(point2, point3),
            Paths.line(point3, point4).linear(point3, point4),
            Paths.line(point4, point5).linear(point4, point5)
    );

    @Override
    public void init() {
        follower = Constants.create(hardwareMap);
        follower.setPose(start);
        follower.update();
    }

    @Override
    public void start() {
        follower.follow(paths);
    }

    @Override
    public void loop() {
        follower.update();
    }
}
