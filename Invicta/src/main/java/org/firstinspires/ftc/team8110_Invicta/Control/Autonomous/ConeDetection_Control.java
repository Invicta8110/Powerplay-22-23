package org.firstinspires.ftc.team8110_Invicta.Control.Autonomous;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.Webcam;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Pipelines.ColorPipeline;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;

public class ConeDetection_Control {
    Webcam camera;

    public ConeDetection_Control(Webcam webcam) {

        camera = webcam;

        // Set up pipeline
        ColorPipeline pipeline = new ColorPipeline();
        camera.getCamera().setPipeline(pipeline);
    }

    public void closeCamera(){
        camera.getCamera().closeCameraDeviceAsync(new OpenCvCamera.AsyncCameraCloseListener() {
            @Override
            public void onClose() {
                camera.getCamera().closeCameraDevice();
                camera.getCamera().stopStreaming();
            }
        });
    }
}
