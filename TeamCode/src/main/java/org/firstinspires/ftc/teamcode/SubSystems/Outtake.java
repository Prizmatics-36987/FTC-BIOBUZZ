package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Outtake {
    DcMotor outtake_left, outtake_right;

    public Outtake(HardwareMap hw) {
        outtake_left = hw.dcMotor.get("outtake_left");
        outtake_right = hw.dcMotor.get("outtake_right");
        outtake_right.setDirection(DcMotorSimple.Direction.REVERSE);

        outtake_left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        outtake_right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void setPower(double power) {
        outtake_left.setPower(power);
        outtake_right.setPower(power);
    }

    public void reverseDirection() {
        outtake_left.setDirection(outtake_right.getDirection().inverted());
        outtake_right.setDirection(outtake_right.getDirection().inverted());
    }
}
