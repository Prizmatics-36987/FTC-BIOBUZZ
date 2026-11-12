package org.firstinspires.ftc.teamcode.OpMode.TeleOp;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.SubSystems.Sub_DriveTrain;
import org.firstinspires.ftc.teamcode.SubSystems.Sub_Intake;
import org.firstinspires.ftc.teamcode.SubSystems.Sub_Outtake;
import org.firstinspires.ftc.teamcode.SubSystems.Sub_Vision;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.vision.VisionPortal;
import org.opencv.core.Point;

@TeleOp(name="M_Controller", group="Linear OpMode")
public class M_Controller extends OpMode {
    Follower follower;
    VisionPortal visionPortal;

    Sub_DriveTrain driveTrain;
    Sub_Intake intake;
    Sub_Outtake outtake;
    Sub_Vision vision;

    boolean intake_active = false;
    boolean outtake_active = false;

    double INTAKE_POWER = 1.0;
    double OUTTAKE_POWER = 0.5;

    double speed;

    @Override
    public void init() {
        follower = Constants.create(hardwareMap);

        driveTrain = new Sub_DriveTrain(hardwareMap, gamepad1);
        intake = new Sub_Intake(hardwareMap);
        outtake = new Sub_Outtake(hardwareMap);
        vision = new Sub_Vision();
    }

    @Override
    public void start() {
        visionPortal = vision.getVisionPortal(hardwareMap);
    }

    @Override
    public void loop() {
        Point target = vision.getLargestCenter();

        if (target != null && gamepad1.y) {
            telemetry.addData("Object X", target.x);
            telemetry.addData("Object Y", target.y);
            telemetry.addData("Horizontal error", target.x - 320.0);

            speed = ((target.x -320.0) / 320) * 0.75;

            intake.setPower(INTAKE_POWER);

            follower.manual(0.5, 0, -speed);
        } else {
            follower.stop();
            driveTrain.drive();

            if (gamepad1.touchpadWasPressed()) {
                driveTrain.snippetGoHome();
            }

            if (gamepad1.aWasPressed()) {
                intake_active = !intake_active;
            }

            if (gamepad1.bWasPressed()) {
                outtake_active = !outtake_active;
            }

            setSubsystemPowers(intake_active, outtake_active);
        }

        follower.update();
    }

    @Override
    public void stop() {
        if (visionPortal != null) {
            visionPortal.close();
        }
    }

    private void setSubsystemPowers(boolean intake_active, boolean outtake_active) {
        if (intake_active) {
            intake.setPower(INTAKE_POWER);
        } else {
            intake.setPower(0);
        }

        if (outtake_active) {
            outtake.setPower(OUTTAKE_POWER);
        } else {
            intake.setPower(0);
        }
    }
}
