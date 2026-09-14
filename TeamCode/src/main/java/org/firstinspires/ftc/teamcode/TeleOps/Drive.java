package org.firstinspires.ftc.teamcode.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.InitMethods;
import org.firstinspires.ftc.teamcode.SubSystems.DriveTrain;

@TeleOp(name="Drive", group="Linear OpMode")
public class Drive extends OpMode {
    @Override
    public void init() {
        InitMethods.initDriveTrain();
    }

    @Override
    public void loop() {
        DriveTrain.drive();
    }
}