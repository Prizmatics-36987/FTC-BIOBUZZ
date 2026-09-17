package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.SubSystems.AprilTag;
import org.firstinspires.ftc.teamcode.SubSystems.ManualDrive;
import org.firstinspires.ftc.teamcode.SubSystems.Intake;

public class Robot {
    private static Robot instance;

    public HardwareMap hw;

    public ManualDrive ManualDrive;
    public Intake Intake;
    public AprilTag AprilTag;

    public static synchronized Robot getInstance() {
        if (instance == null) {
            instance = new Robot();
        }
        return instance;
    }
}
