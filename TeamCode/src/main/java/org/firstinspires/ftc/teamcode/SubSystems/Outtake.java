package org.firstinspires.ftc.teamcode.SubSystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class Outtake extends SubsystemBase {
    public static DcMotor motor1, motor2;

    public Outtake() {
        motor1 = hardwareMap.dcMotor.get("outtake1");
        motor2 = hardwareMap.dcMotor.get("outtake2");

        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void setPower(double power) {
        motor1.setPower(power);
        motor2.setPower(power);
    }
}
