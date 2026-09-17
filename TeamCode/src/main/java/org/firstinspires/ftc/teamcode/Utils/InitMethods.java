package org.firstinspires.ftc.teamcode.Utils;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.SubSystems.ManualDrive;
import org.firstinspires.ftc.teamcode.SubSystems.Intake;

public class InitMethods {
    static Robot robot = Robot.getInstance();

    public static void initTeleOp() {
        robot.ManualDrive = new ManualDrive();
        robot.Intake = new Intake();
    }

    public static void initAutoTest() {
        robot.Intake = new Intake();
    }
}