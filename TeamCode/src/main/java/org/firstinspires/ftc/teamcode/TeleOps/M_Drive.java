package org.firstinspires.ftc.teamcode.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.SubSystems.ManualDrive;

@TeleOp(name="Drive", group="Linear OpMode")
public class M_Drive extends OpMode {
    ManualDrive drive;

    @Override
    public void init() {
        drive = new ManualDrive();
    }

    @Override
    public void loop() {
        drive.drive(true);
    }
}