package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Robot;

public class Intake {
    Robot robot = Robot.getInstance();

    private static DcMotor motor;

    public Intake() {
        motor = robot.hw.dcMotor.get("intake");
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public static void activate(double power) {
        motor.setPower(power);
    }

    public static boolean is_active() {
        return motor.getPower() != 0;
    }
}
