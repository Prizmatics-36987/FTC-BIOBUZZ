package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {
    private static Robot instance;
    public Systems Systems;

    public static synchronized Robot getInstance() {
        if (instance == null) {
            instance = new Robot();
        }
        return instance;
    }

    public synchronized void resetRobot() {
        instance = null;
    }

    public void init(Gamepad gamepad1, HardwareMap hw) {
        Systems = new Systems(gamepad1, hw);
    }
}
