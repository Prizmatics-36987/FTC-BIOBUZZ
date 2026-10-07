package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Outtake {
    DcMotor motor1, motor2;

    public Outtake(HardwareMap hardwareMap) {
        motor1 = hardwareMap.dcMotor.get("outtake1");
        motor2 = hardwareMap.dcMotor.get("outtake2");
        motor1.setDirection(DcMotorSimple.Direction.REVERSE);

        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void setPower(double power) {
        motor1.setPower(power);
        motor2.setPower(power);
    }

    public void reverseDirection() {
        motor1.setDirection(motor1.getDirection().inverted());
        motor2.setDirection(motor2.getDirection().inverted());
    }
}
