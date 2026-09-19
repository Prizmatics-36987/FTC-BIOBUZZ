package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.SubSystems.AprilTag;
import org.firstinspires.ftc.teamcode.SubSystems.ManualDrive;
import org.firstinspires.ftc.teamcode.SubSystems.Intake;

public class Robot {
    private static Robot instance;

    public HardwareMap hw;

    public static ManualDrive ManualDrive;
    public static Intake Intake;
    public static AprilTag AprilTag;

    public static synchronized Robot getInstance() {
        if (instance == null) {
            instance = new Robot();
        }
        return instance;
    }

    public static void initTeleOpDrive() {
        ManualDrive = new ManualDrive();
        Intake = new Intake();
    }

    public static void initAutoTest() {
        Intake = new Intake();
    }

    public static void initAprilTagTest() {
        AprilTag = new AprilTag();
    }
}
