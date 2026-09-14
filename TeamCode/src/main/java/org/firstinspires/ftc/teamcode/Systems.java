package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.SubSystems.DriveTrain;

public class Systems {
    public Gamepad gamepad1;
    public HardwareMap hw;
    public DcMotor intake;

    public DriveTrain DriveTrain;

    public Systems(Gamepad gamepad1, HardwareMap hw, DcMotor intake) {
        this.gamepad1 = gamepad1;
        this.hw = hw;
        this.intake = intake;
    }
}
