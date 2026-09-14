package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.SubSystems.DriveTrain;

public class Robot {
    private static Robot instance;

    // Hardware
    public Gamepad gamepad1;
    public HardwareMap hw;

    // SubSystems
    public DriveTrain DriveTrain;

    public static synchronized Robot getInstance() {
        if (instance == null) {
            instance = new Robot();
        }
        return instance;
    }
}
