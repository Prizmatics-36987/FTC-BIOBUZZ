package org.firstinspires.ftc.teamcode.Utils;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.SubSystems.DriveTrain;
import org.firstinspires.ftc.teamcode.SubSystems.Intake;

public class InitMethods {
    static Robot robot = Robot.getInstance();

    public static void initOpModeDrive() {
        robot.DriveTrain = new DriveTrain();
        robot.Intake = new Intake();
    }
}