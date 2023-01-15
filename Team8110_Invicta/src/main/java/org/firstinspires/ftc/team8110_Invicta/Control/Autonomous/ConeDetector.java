package org.firstinspires.ftc.team8110_Invicta.Control.Autonomous;

import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.Webcam;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Pipelines.ColorPipeline;
import org.openftc.easyopencv.OpenCvCamera;

public class ConeDetector {
    Webcam webcam;
    OpenCvCamera camera;

    public ConeDetector(Webcam webcam) {

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
