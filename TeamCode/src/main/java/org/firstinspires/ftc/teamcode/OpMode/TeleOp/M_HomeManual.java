package org.firstinspires.ftc.teamcode.OpMode.TeleOp;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.SubSystems.Sub_DriveTrain;

@TeleOp(name="M_HomeManual", group="Linear OpMode")
public class M_HomeManual extends OpMode {
    Follower follower;

    @Override
    public void init() {
        follower = Sub_DriveTrain.getFollower();
    }

    @Override
    public void loop() {
        Sub_DriveTrain.drive(follower);

        if (gamepad1.touchpadWasPressed()) {
            Sub_DriveTrain.goHome(follower);
        }
    }
}
