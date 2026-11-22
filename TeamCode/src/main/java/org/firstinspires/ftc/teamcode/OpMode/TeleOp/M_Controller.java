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

    final double INTAKE_POWER = 0.75;
    final double OUTTAKE_POWER = 0.75;

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

        if (gamepad1.xWasPressed()) {
            driveTrain.setHome();
        }

        if (gamepad1.aWasPressed()) {
            intake_active = !intake_active;
        }

        if (gamepad1.bWasPressed()) {
            outtake_active = !outtake_active;
        }

        intake.setPower(intake_active ? INTAKE_POWER : 0);
        outtake.setPower(outtake_active ? OUTTAKE_POWER : 0);
    }
}
