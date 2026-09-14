package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Robot;

public class Intake extends SubsystemBase {
    Robot robot = Robot.getInstance();

    private static DcMotor motor;

    Intake() {
        motor = robot.Systems.hw.dcMotor.get("intake");
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    static void activate(double power) {
        motor.setPower(power);
    }
}
