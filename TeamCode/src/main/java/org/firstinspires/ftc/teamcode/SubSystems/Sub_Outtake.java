package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Sub_Outtake {
    Servo aimingServo;
    DcMotor motorLeft, motorRight;

    public Sub_Outtake(HardwareMap hardwareMap) {
//        aimingServo = hardwareMap.servo.get("aiming_servo");

        motorLeft = hardwareMap.dcMotor.get("outtake_left");
        motorRight = hardwareMap.dcMotor.get("outtake_right");

        setDirection(DcMotorSimple.Direction.FORWARD);

        motorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void setPower(double power) {
        motorLeft.setPower(power);
        motorRight.setPower(power);
    }

    public void setDirection(DcMotorSimple.Direction direction) {
        motorLeft.setDirection(direction);
        motorRight.setDirection(direction.inverted());
    }

    public DcMotorSimple.Direction getDirection() {
        return motorLeft.getDirection();
    }

    public void reverseDirection() {
        setDirection(getDirection().inverted());
    }

//    public void setAimingServoPosition(double position) {
//        aimingServo.setPosition(position);
//    }
//
//    public double getAimingServoPosition() {
//        return aimingServo.getPosition();
//    }
}
