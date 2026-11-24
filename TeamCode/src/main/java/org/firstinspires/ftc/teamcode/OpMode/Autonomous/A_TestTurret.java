package org.firstinspires.ftc.teamcode.OpMode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.SubSystems.Sub_DriveTrain;
import org.firstinspires.ftc.teamcode.SubSystems.Sub_Outtake;

@Autonomous(name="A_TestTurret", group="Linear OpMode")
public class A_TestTurret extends OpMode {
    Sub_DriveTrain driveTrain;
    Sub_Outtake outtake;

    @Override
    public void init() {
        driveTrain = new Sub_DriveTrain(hardwareMap, gamepad1);
        outtake = new Sub_Outtake(hardwareMap);
    }

    @Override
    public void loop() {
        driveTrain.drive();

        outtake.setTurretPosition(100);
        telemetry.addData("pos: ", outtake.getTurretPosition());
        telemetry.update();

        if (gamepad1.touchpadWasPressed()) {
            driveTrain.snippetGoHome();
        }
    }
}
