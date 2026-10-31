package org.firstinspires.ftc.teamcode.OpMode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.SubSystems.Sub_DriveTrain;

@TeleOp(name="M_TestDriveTrain", group="Linear OpMode")
public class M_TestDriveTrain extends OpMode {
    Sub_DriveTrain driveTrain;

    @Override
    public void init() {
        driveTrain = new Sub_DriveTrain(hardwareMap, gamepad1);
    }

    @Override
    public void loop() {
        driveTrain.drive();

        if (gamepad1.touchpadWasPressed()) {
            driveTrain.snippetGoHome();
        }
    }
}
