package org.firstinspires.ftc.teamcode.OpMode.Utillities;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Utility;

import org.firstinspires.ftc.teamcode.SubSystems.Sub_Vision;
import org.firstinspires.ftc.vision.VisionPortal;

@Utility(name="U_Visuzlize")
public class U_Visualize extends OpMode {

    private Sub_Vision vision;
    private VisionPortal visionPortal;

    @Override
    public void init() {
        vision = new Sub_Vision();
        visionPortal = vision.getVisionPortal(hardwareMap);
    }

    @Override
    public void loop() {

    }
}
