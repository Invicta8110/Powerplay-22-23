package org.firstinspires.ftc.team8109_Rise.Hardware.Sensors.Camera.OpenCV;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.team8109_Rise.Hardware.Sensors.Camera.OpenCV.VisionPipelines.Signal_Identifier;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

public class WebcamSetup {
    OpenCvCamera webcam;

    Telemetry telemetry;
    HardwareMap hardwareMap;
    public WebcamSetup(OpenCvCamera webcam, Telemetry telemetry, HardwareMap hardwareMap){
        this.webcam = webcam;
        this.telemetry = telemetry;
        this.hardwareMap = hardwareMap;
    }

    public void cameraSetup(){

    }
}
