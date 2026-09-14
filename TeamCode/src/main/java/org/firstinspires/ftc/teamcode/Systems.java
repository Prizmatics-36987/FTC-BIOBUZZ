package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.CommandScheduler;

import org.firstinspires.ftc.teamcode.SubSystems.DriveTrain;

public class Systems {
    public Gamepad gamepad1;
    public HardwareMap hw;

    public DriveTrain DriveTrain;

    public Systems(Gamepad gamepad1, HardwareMap hw) {
        this.gamepad1 = gamepad1;
        this.hw = hw;

        CommandScheduler.getInstance().reset();
    }
}
