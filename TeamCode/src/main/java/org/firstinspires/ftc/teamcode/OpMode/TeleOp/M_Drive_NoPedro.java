package org.firstinspires.ftc.teamcode.OpMode.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.SubSystems.Sub_DriveTrain_NoPedro;

@TeleOp(name="M_Drive_NoPedro", group="Linear OpMode")
public class M_Drive_NoPedro extends OpMode {

    public Sub_DriveTrain_NoPedro driveTrain;

    double power = 1;

    @Override
    public void init() {

        driveTrain = new Sub_DriveTrain_NoPedro(hardwareMap);

    }

    @Override
    public void loop() {
        telemetry.addData("power:", power);

        if (gamepad1.dpad_down) {
            driveTrain.go_backwards(power);
        }

        if (gamepad1.dpad_up) {
            driveTrain.go_forwards(power);
        }

        if (gamepad1.dpad_right) {
            driveTrain.go_right(power);
        }

        if (gamepad1.dpad_left) {
            driveTrain.go_left(power);
        }

        if (gamepad1.atRest()) {
            driveTrain.brake();
        }

        if (gamepad1.leftBumperWasPressed()) {
            power -= 0.25;
        }

        if (gamepad1.rightBumperWasPressed()) {
            power += 0.25;
        }

        telemetry.update();
    }
}
