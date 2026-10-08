package org.firstinspires.ftc.teamcode.OpMode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.SubSystems.DriveTrain_NoPedro;
import org.firstinspires.ftc.teamcode.SubSystems.Outtake;

@TeleOp(name = "M_Controller_No_Pedro", group = "Linear OpMode")
public class M_Controller_No_Pedro extends OpMode {
    DriveTrain_NoPedro driveTrain;
    Outtake outtake;

    double driveTrainPower = 1;
    double outtakePower = 0.5;


    @Override
    public void init() {
        driveTrain = new DriveTrain_NoPedro(hardwareMap);
        outtake = new Outtake(hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("drive train power: ", driveTrainPower);
        telemetry.addData("outtake power: ", outtakePower);
        telemetry.addData("direction: ", outtake.getDirection());

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

        if (gamepad1.leftTriggerWasPressed() && outtakePower > 0) {
            outtakePower -= 0.25;
        }

        if (gamepad1.rightTriggerWasPressed() && outtakePower < 1) {
            outtakePower += 0.25;
        }

        if (gamepad1.triangleWasPressed()) {
            outtake.reverseDirection();
        }

        outtake.setPower(outtakePower);
        telemetry.update();
    }
}
