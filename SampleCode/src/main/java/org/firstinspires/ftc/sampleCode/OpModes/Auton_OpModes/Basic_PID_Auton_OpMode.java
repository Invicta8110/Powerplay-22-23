package org.firstinspires.ftc.sampleCode.OpModes.Auton_OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.sampleCode.Control.Autonomous.Basic_PID_Drive_Auton_Control;

public class Basic_PID_Auton_OpMode extends LinearOpMode {
    @Override
    public void runOpMode() {
        Basic_PID_Drive_Auton_Control auton = new Basic_PID_Drive_Auton_Control("fLeft", "fRight", "bRight", "bLeft", hardwareMap, telemetry);

        telemetry.addLine("Waiting for start");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()){
            auton.Drive();
            auton.Telemetry();
        }
    }
}
