package org.firstinspires.ftc.teamcode.OpMode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.SubSystems.Sub_Outtake;

@TeleOp(name="M_TestOuttake", group="Linear OpMode")
public class M_TestOuttake extends OpMode {
    Sub_Outtake outtake;

    double power;
    double angle;

    final double MAX_POWER = 1.0;
    final double MIN_POWER = 1.0;

    @Override
    public void init() {
        outtake = new Sub_Outtake(hardwareMap);

        power = 0.5;
        angle = 0.5;
    }

    @Override
    public void loop() {
        if (gamepad1.aWasPressed()) {
            power += 0.25;
        } else if (gamepad1.bWasPressed()) {
            power -= 0.25;
        } else if (gamepad1.leftTriggerWasPressed()) {
            outtake.reverseDirection();
        }

        if (gamepad1.xWasPressed()) {
            angle += 0.25;
        } else if (gamepad1.yWasPressed()) {
            angle -= 0.25;
        }

        power = Math.max(Math.min(power, MAX_POWER), MIN_POWER);
        angle = Math.max(Math.min(angle, Servo.MAX_POSITION), Servo.MIN_POSITION);

        outtake.setPower(power);
//        outtake.setAimingServoPosition(angle);

        telemetry.addData("power: ", power);
//        telemetry.addData("servo angle: ", angle);
        telemetry.update();
    }
}