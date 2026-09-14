package org.firstinspires.ftc.teamcode.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.InitMethods;

@TeleOp(name="Drive", group="Linear OpMode")
public class Drive extends CommandOpMode {
    @Override
    public void initialize() {
        InitMethods.initDriveTrain();
    }

    @Override
    public void run() {
        super.run();
    }
}