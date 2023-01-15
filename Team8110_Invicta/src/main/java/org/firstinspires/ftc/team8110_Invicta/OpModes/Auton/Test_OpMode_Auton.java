package org.firstinspires.ftc.team8110_Invicta.OpModes.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Disabled
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
