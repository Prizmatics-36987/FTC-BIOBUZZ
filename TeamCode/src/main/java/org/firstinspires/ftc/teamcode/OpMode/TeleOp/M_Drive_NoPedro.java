package org.firstinspires.ftc.teamcode.OpMode.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.SubSystems.ManualDrive_NoPedro;

@TeleOp(name="M_Drive_NoPedro", group="Linear OpMode")
public class M_Drive_NoPedro extends OpMode {

    public ManualDrive_NoPedro manualDriveNoPedro;

    double power = 1;

    @Override
    public void init() {

        manualDriveNoPedro = new ManualDrive_NoPedro(hardwareMap);

    }

    @Override
    public void loop() {
        telemetry.addData("power:", power);

        if (gamepad1.dpad_down) {
            manualDriveNoPedro.go_backwards(power);
        }

        if (gamepad1.dpad_up) {
            manualDriveNoPedro.go_forwards(power);
        }

        if (gamepad1.dpad_right) {
            manualDriveNoPedro.go_right(power);
        }

        if (gamepad1.dpad_left) {
            manualDriveNoPedro.go_left(power);
        }

        if (gamepad1.atRest()) {
            manualDriveNoPedro.brake();
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
