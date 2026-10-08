package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Outtake {
    DcMotor outtake_left, outtake_right;

    public Outtake(HardwareMap hardwareMap) {
        outtake_left = hardwareMap.dcMotor.get("outtake_left");
        outtake_right = hardwareMap.dcMotor.get("outtake_right");

        setDirection(DcMotorSimple.Direction.FORWARD);

        outtake_left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        outtake_right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void setPower(double power) {
        outtake_left.setPower(power);
        outtake_right.setPower(power);
    }

    public void setDirection(DcMotorSimple.Direction direction) {
        outtake_left.setDirection(direction);
        outtake_right.setDirection(direction.inverted());
    }

    public DcMotorSimple.Direction getDirection() {
        return outtake_left.getDirection();
    }

    public void reverseDirection() {
        setDirection(getDirection().inverted());
    }
}
