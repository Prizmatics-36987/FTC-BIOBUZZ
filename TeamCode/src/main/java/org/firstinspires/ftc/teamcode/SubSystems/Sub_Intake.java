package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Sub_Intake {
    DcMotor motor;

    public Sub_Intake(HardwareMap hw) {
        motor = hw.dcMotor.get("intake");
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void setPower(double power) {
        motor.setPower(power);
    }

    public void reverseDirection() {
        motor.setDirection(
                motor.getDirection().inverted()
        );
    }
}
