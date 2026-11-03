package org.firstinspires.ftc.teamcode.OpMode.Autonomous;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.SubSystems.Sub_Intake;
import org.firstinspires.ftc.teamcode.SubSystems.Sub_Vision;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.vision.VisionPortal;
import org.opencv.core.Point;

@Autonomous(name = "A_TestVision", group = "Linear OpMode")
public class A_TestVision extends OpMode {
    private Follower follower;
    private VisionPortal visionPortal;
    private Sub_Vision vision;

    private Sub_Intake intake;

    double speed;

    @Override
    public void init() {
        follower = Constants.create(hardwareMap);
        vision = new Sub_Vision();
        intake = new Sub_Intake(hardwareMap);
    }

    @Override
    public void start() {
        visionPortal = vision.getVisionPortal(hardwareMap);
    }

    @Override
    public void loop() {
        Point target = vision.getLargestCenter();

        if (target != null) {
            telemetry.addData("Object X", target.x);
            telemetry.addData("Object Y", target.y);
            telemetry.addData("Horizontal error", target.x - 320.0);

            speed = ((target.x -320.0) / 320) * 0.75;

            intake.setPower(1.0);

            follower.manual(0.5, 0, -speed);
        } else {
            telemetry.addLine("No object detected");

            intake.setPower(0.0);

            follower.manual(0, 0,  0.4);
        }

        follower.update();
        telemetry.update();
    }

    @Override
    public void stop() {
        if (visionPortal != null) {
            visionPortal.close();
        }
    }
}