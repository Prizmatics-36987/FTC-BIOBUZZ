package org.firstinspires.ftc.teamcode.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.SubSystems.ManualDrive;

@TeleOp(name="Drive", group="Linear OpMode")
public class M_Drive extends OpMode {
    @Override
    public void init() {
        Robot.initTeleOpDrive();
    }

    @Override
    public void loop() {
        ManualDrive.drive(true);
    }
}