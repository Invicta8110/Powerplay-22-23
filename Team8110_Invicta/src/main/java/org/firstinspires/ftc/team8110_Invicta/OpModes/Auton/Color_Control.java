package org.firstinspires.ftc.team8110_Invicta.OpModes.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//import org.firstinspires.ftc.team8110_Invicta.Hardware.Sensors.Camera.OpenCV.VisionPipelines.Colordetector;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Sensors.Colordetector;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous
public class Color_Control extends LinearOpMode {

    //initialize Webcam
    OpenCvCamera camera;

    //initialize & create Pipeline
    Colordetector pipeline;
//
//    @Override
    public void runOpMode() {

        //set up camera
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Camera1"), cameraMonitorViewId);

        //set up pipeline
        pipeline = new Colordetector();
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
            //updates each time
            telemetry.update();
        }
    }
}


