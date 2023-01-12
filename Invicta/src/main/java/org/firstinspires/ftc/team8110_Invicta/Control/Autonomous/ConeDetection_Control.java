package org.firstinspires.ftc.team8110_Invicta.Control.Autonomous;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.Webcam;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Pipelines.ColorPipeline;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;

public class ConeDetection_Control {
    Webcam webcam;
    OpenCvCamera camera;

    public ConeDetection_Control(Webcam webcam) {

        this.webcam = webcam;
        camera = webcam.getCamera();

        // Set up pipeline
        ColorPipeline pipeline = new ColorPipeline();
        camera.setPipeline(pipeline);
    }

    public void closeCamera(){
        camera.closeCameraDeviceAsync(() -> {
            camera.closeCameraDevice();
            camera.stopStreaming();
        });
    }
}
