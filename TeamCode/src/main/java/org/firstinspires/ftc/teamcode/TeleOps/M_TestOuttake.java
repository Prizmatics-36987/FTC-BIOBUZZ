package org.firstinspires.ftc.teamcode.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.SubSystems.Intake;

@TeleOp(name="M_TestOuttake", group="Linear OpMode")
public class M_TestOuttake extends OpMode {
    Intake intake;

    double power;

    @Override
    public void init() {
        intake = new Intake();

        power = 0.5;
    }

    @Override
    public void loop() {
        power = Math.max(Math.min(power, 1), 0);
        intake.setPower(power);

        if (gamepad1.a) {
            power += 0.25;
        } else if (gamepad1.b) {
            power -= 0.25;
        }

        telemetry.addData("power: ", power);
        telemetry.update();
    }
}