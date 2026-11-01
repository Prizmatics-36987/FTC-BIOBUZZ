package org.firstinspires.ftc.teamcode.OpMode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.SubSystems.Sub_Intake;
import org.firstinspires.ftc.teamcode.SubSystems.Sub_Outtake;

@TeleOp(name="M_TestIntake", group="Linear OpMode")
public class M_TestIntake extends OpMode {
    Sub_Intake intake;
    double power;

    @Override
    public void init() {
        intake = new Sub_Intake(hardwareMap);

        power = 0.5;
    }

    @Override
    public void loop() {
        power = Math.max(Math.min(power, 1), 0);
        intake.setPower(power);

        if (gamepad1.aWasPressed()) {
            power += 0.25;
        } else if (gamepad1.bWasPressed()) {
            power -= 0.25;
        } else if (gamepad1.leftTriggerWasPressed()) {
            intake.reverseDirection();
        }

        telemetry.addData("power: ", power);
        telemetry.update();
    }
}