package org.firstinspires.ftc.sampleCode.OpModes.Auton_OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.sampleCode.Control.Autonomous.RoadRunner_Example_Auton_Control;

@Autonomous
public class RoadRunner_Example_Auton_OpMode extends LinearOpMode {

    @Override
    public void runOpMode() {

        RoadRunner_Example_Auton_Control control = new RoadRunner_Example_Auton_Control("frontLeft", "frontRight", "backRight", "backLeft", hardwareMap, telemetry);

        telemetry.addLine("Waiting for start");
        telemetry.update();

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive() && !isStopRequested()){
            control.RR_Drive();

        }
    }
}
