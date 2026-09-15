package org.firstinspires.ftc.teamcode.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Utils.InitMethods;
import org.firstinspires.ftc.teamcode.SubSystems.ManualDrive;

@TeleOp(name="Drive", group="Linear OpMode")
public class Drive extends OpMode {
    @Override
    public void init() {
        InitMethods.initOpModeDrive();
    }

    @Override
    public void loop() {
        ManualDrive.drive(true);
    }
}