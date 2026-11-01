package org.firstinspires.ftc.teamcode.OpMode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.SubSystems.Sub_DriveTrain;
import org.firstinspires.ftc.teamcode.SubSystems.Sub_Intake;
import org.firstinspires.ftc.teamcode.SubSystems.Sub_Outtake;

@TeleOp(name="M_Controller", group="Linear OpMode")
public class M_Controller extends OpMode {
    Sub_DriveTrain driveTrain;
    Sub_Intake intake;
    Sub_Outtake outtake;

    boolean intake_active = false;
    boolean outtake_active = false;

    double INTAKE_POWER = 0.5;
    double OUTTAKE_POWER = 0.5;

    @Override
    public void init() {
        driveTrain = new Sub_DriveTrain(hardwareMap, gamepad1);
        intake = new Sub_Intake(hardwareMap);
        outtake = new Sub_Outtake(hardwareMap);
    }

    @Override
    public void loop() {
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
