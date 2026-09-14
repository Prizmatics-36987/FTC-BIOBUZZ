package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.SubSystems.DriveTrain;

public class InitMethods {
    static Robot robot = Robot.getInstance();
    static Systems systems = robot.Systems;

    public static void initDriveTrain() {
        robot.init(systems.gamepad1, systems.hw, systems.intake);
        systems.DriveTrain = new DriveTrain();
    }
}