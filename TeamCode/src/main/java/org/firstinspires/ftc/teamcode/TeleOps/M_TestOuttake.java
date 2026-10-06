package org.firstinspires.ftc.teamcode.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.SubSystems.Outtake;

@TeleOp(name="M_TestOuttake", group="Linear OpMode")
public class M_TestOuttake extends OpMode {
    Outtake outtake;
    double power;

    @Override
    public void init() {
        outtake = new Outtake(hardwareMap);

        power = 0.5;
    }

    @Override
    public void loop() {
        power = Math.max(Math.min(power, 1), 0);
        outtake.setPower(power);

        if (gamepad1.aWasPressed()) {
            power += 0.25;
        } else if (gamepad1.bWasPressed()) {
            power -= 0.25;
        } else if (gamepad1.leftTriggerWasPressed()) {
            outtake.reverseDirection();
        }

        telemetry.addData("power: ", power);
        telemetry.update();
    }
}