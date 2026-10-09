package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Sub_Outtake {
    Servo aimingServo;
    DcMotor outtakeLeft, outtakeRight;

    public Sub_Outtake(HardwareMap hardwareMap) {
        aimingServo = hardwareMap.servo.get("aiming_servo");

        outtakeLeft = hardwareMap.dcMotor.get("outtake_left");
        outtakeRight = hardwareMap.dcMotor.get("outtake_right");

        setDirection(DcMotorSimple.Direction.FORWARD);

        outtakeLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        outtakeRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void setPower(double power) {
        outtakeLeft.setPower(power);
        outtakeRight.setPower(power);
    }

    public void setDirection(DcMotorSimple.Direction direction) {
        outtakeLeft.setDirection(direction);
        outtakeRight.setDirection(direction.inverted());
    }

    public DcMotorSimple.Direction getDirection() {
        return outtakeLeft.getDirection();
    }

    public void reverseDirection() {
        setDirection(getDirection().inverted());
    }

    public void setAimingServoPosition(double position) {
        aimingServo.setPosition(position);
    }

    public double getAimingServoPosition() {
        return aimingServo.getPosition();
    }
}
