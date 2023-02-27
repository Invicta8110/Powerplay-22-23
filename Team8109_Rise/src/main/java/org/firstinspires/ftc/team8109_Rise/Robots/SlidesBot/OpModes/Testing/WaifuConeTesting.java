package org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.OpModes.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Sensors.Camera.MachineLearning.YOLO_Model;

@Autonomous
public class WaifuConeTesting extends LinearOpMode {

    YOLO_Model waifuConeModel;

    @Override
    public void runOpMode() throws InterruptedException {
        waifuConeModel = new YOLO_Model(telemetry, hardwareMap);

//        while (opModeInInit()){
//            waifuConeModel.Detection();
//            telemetry.update();
//        }

        waitForStart();

        while (opModeIsActive()){
            waifuConeModel.Detection();
            telemetry.update();
        }
    }
}
