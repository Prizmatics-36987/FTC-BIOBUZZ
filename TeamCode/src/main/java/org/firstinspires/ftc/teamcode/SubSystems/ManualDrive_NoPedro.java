package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class ManualDrive_NoPedro extends SubsystemBase {
    private final double movementMultiplier = 0.5;

    private final DcMotor front_left, front_right, back_left, back_right;

    public ManualDrive_NoPedro(HardwareMap hardwareMap) {
        front_left = hardwareMap.dcMotor.get("front_left");
        front_right = hardwareMap.dcMotor.get("front_right");
        back_left = hardwareMap.dcMotor.get("back_left");
        back_right = hardwareMap.dcMotor.get("back_right");

        front_right.setDirection(DcMotorSimple.Direction.REVERSE);
        back_right.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void go_forwards(double amount) {
        front_left.setPower(amount * movementMultiplier);
        front_right.setPower(amount * movementMultiplier);
        back_left.setPower(amount * movementMultiplier);
        back_right.setPower(amount * movementMultiplier);
    }

    public void go_backwards(double amount) {
        front_left.setPower(amount * -movementMultiplier);
        front_right.setPower(amount * -movementMultiplier);
        back_left.setPower(amount * -movementMultiplier);
        back_right.setPower(amount * -movementMultiplier);
    }

    public void go_left(double amount) {
        front_left.setPower(amount * -movementMultiplier);
        front_right.setPower(amount * movementMultiplier);
        back_left.setPower(amount * movementMultiplier);
        back_right.setPower(amount * -movementMultiplier);
    }

    public void go_right(double amount) {
        front_left.setPower(amount * movementMultiplier);
        front_right.setPower(amount * -movementMultiplier);
        back_left.setPower(amount * -movementMultiplier);
        back_right.setPower(amount * movementMultiplier);
    }

    public void brake() {
        front_left.setPower(0);
        front_right.setPower(0);
        back_left.setPower(0);
        back_right.setPower(0);
    }
}