package org.firstinspires.ftc.workshop_Code.OpModes.Auton_OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class Test_OpMode_Auton extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addLine("Test Hi");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()){
            telemetry.addLine("Test bye");
            telemetry.update();
        }
    }
}
