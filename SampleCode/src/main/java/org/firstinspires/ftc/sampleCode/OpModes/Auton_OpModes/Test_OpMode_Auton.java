package org.firstinspires.ftc.sampleCode.OpModes.Auton_OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.sampleCode.Control.Autonomous.MotorTest;

@Autonomous
public class Test_OpMode_Auton extends LinearOpMode {
    @Override
    public void runOpMode() {

        MotorTest test = new MotorTest("motor", hardwareMap);

        telemetry.addLine("Test Hi");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()){
            test.MotorRun();

            telemetry.addLine("Test motor");
            telemetry.update();
        }
    }
}
