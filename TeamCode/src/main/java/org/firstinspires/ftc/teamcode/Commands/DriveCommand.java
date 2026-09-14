package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.RunCommand;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.Systems;

public class DriveCommand extends RunCommand {
    static Systems systems = Robot.getInstance().Systems;

    public DriveCommand() {
        super(
                () -> systems.DriveTrain.drive(), systems.DriveTrain
        );
    }
}
