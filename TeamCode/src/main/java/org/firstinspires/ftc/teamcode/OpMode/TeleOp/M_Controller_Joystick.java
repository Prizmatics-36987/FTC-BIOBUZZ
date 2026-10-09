package org.firstinspires.ftc.teamcode.OpMode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.SubSystems.Sub_DriveTrain_NoPedro;
import org.firstinspires.ftc.teamcode.SubSystems.Sub_Outtake;

@TeleOp(name = "M_Controller_Joystick", group = "Linear OpMode")
public class M_Controller_Joystick extends OpMode {
    Sub_DriveTrain_NoPedro driveTrain;
    Sub_Outtake outtake;

    double driveTrainPower = 1;

    @Override
    public void init() {
        driveTrain = new Sub_DriveTrain_NoPedro(hardwareMap);
        outtake = new Sub_Outtake(hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("drive train power: ", driveTrainPower);
        telemetry.addData("outtake direction: ", outtake.getDirection());
        telemetry.addData("aiming servo position: ", outtake.getAimingServoPosition());

        if (gamepad1.dpad_up) {
            driveTrain.go_forwards(driveTrainPower);
        }

        if (gamepad1.dpad_down) {
            driveTrain.go_backwards(driveTrainPower);
        }

        if (gamepad1.dpad_right) {
            driveTrain.go_right(driveTrainPower);
        }

        if (gamepad1.dpad_left) {
            driveTrain.go_left(driveTrainPower);
        }

        if (gamepad1.atRest()) {
            driveTrain.brake();
        }

        if (gamepad1.leftBumperWasPressed() && driveTrainPower > 0) {
            driveTrainPower -= 0.25;
        }

        if (gamepad1.rightBumperWasPressed() && driveTrainPower < 1) {
            driveTrainPower += 0.25;
        }

        if (gamepad1.circleWasPressed()) {
            outtake.reverseDirection();
        }

        outtake.setAimingServoPosition(gamepad1.left_stick_y);
        outtake.setPower(gamepad1.right_stick_y);

        telemetry.update();
    }
}
