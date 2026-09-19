package org.firstinspires.ftc.teamcode.SubSystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Intake {
    private static DcMotor motor;

    public Intake() {
        motor = hardwareMap.dcMotor.get("intake");
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public static void activate(double power) {
        motor.setPower(power);
    }

    public static boolean is_active() {
        return motor.getPower() != 0;
    }
}
