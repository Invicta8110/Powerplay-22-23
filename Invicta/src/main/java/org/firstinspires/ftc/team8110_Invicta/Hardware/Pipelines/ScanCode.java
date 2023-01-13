package org.firstinspires.ftc.team8110_Invicta.Hardware.Pipelines;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team8110_Invicta.Control.Autonomous.ConeDetection_Control;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.Webcam;

@Autonomous
public class ScanCode extends LinearOpMode {

    Webcam webcam;

    @Override
    public void runOpMode() throws InterruptedException {
        webcam = new Webcam(hardwareMap);

        ConeDetection_Control cone = new ConeDetection_Control(webcam, telemetry);

        waitForStart();

        while (opModeIsActive()){
            cone.detectCone();
        }
    }
}
