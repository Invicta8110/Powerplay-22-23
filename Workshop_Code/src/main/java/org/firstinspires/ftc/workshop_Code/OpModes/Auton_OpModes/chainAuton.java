package org.firstinspires.ftc.workshop_Code.OpModes.Auton_OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.workshop_Code.Control.Autonomous.Chain_Auton_Control;

public class chainAuton extends LinearOpMode {

    @Override
    public void runOpMode() {
        Chain_Auton_Control auton = new Chain_Auton_Control("chainMotor", hardwareMap, telemetry);

        telemetry.addLine("Waiting for start");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()){
            auton.chainMotion();
        }
    }
}
