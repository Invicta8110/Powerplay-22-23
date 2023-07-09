package org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.OpModes.Testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Sensors.Camera.MachineLearning.YOLO_Model;

@TeleOp
public class MachineLearningTesting extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        YOLO_Model model = new YOLO_Model(telemetry, hardwareMap);

        while (opModeInInit()){
            model.Detection();
        }

        while (opModeIsActive()){
            model.Detection();
        }
    }
}
