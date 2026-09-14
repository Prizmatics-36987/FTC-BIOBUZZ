package org.firstinspires.ftc.teamcode.Utils;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.SubSystems.DriveTrain;

public class InitMethods {
    static Robot robot = Robot.getInstance();

    public static void initDriveTrain() {
        robot.DriveTrain = new DriveTrain();
    }
}