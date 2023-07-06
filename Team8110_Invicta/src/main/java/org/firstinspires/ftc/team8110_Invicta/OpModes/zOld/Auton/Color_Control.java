package org.firstinspires.ftc.team8110_Invicta.OpModes.zOld.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.Webcam;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Pipelines.ColorDetector;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous
public class Color_Control extends LinearOpMode {

    //initialize Webcam
    OpenCvCamera camera;

    //initialize & create Pipeline
    ColorDetector pipeline;
//
//
    @Override
    public void runOpMode() {

        //set up camera
        camera = new Webcam(hardwareMap).getCamera();

        //set up pipeline
        pipeline = new ColorDetector();
        camera.setPipeline(pipeline);

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {

            @Override
            public void onOpened() {
                camera.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });

        telemetry.addLine("Ready to start!");
        telemetry.update();

        waitForStart();

        while(opModeIsActive()){
            //prints the HSV from array
            telemetry.addData("Region 1", "%7d,%7d,%7d",pipeline.hsv_Value[0], pipeline.hsv_Value[1], pipeline.hsv_Value[2]);
            telemetry.addData("Hue", pipeline.getColor());
            //updates each time
            telemetry.update();
        }
    }
}


