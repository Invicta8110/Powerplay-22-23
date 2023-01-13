package org.firstinspires.ftc.team8110_Invicta.Control.Autonomous;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.Webcam;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Pipelines.ColorPipeline;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class ConeDetection_Control {
    Webcam camera;
    ColorPipeline pipeline;
    Telemetry telemetry;

    public ConeDetection_Control(Webcam webcam, Telemetry telemetry) {

        camera = webcam;

        // Set up pipeline
        pipeline = new ColorPipeline();
        camera.getCamera().setPipeline(pipeline);
        this.telemetry = telemetry;

        camera.getCamera().openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.getCamera().startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {
                /*
                 * This will be called if the camera could not be opened
                 */
            }

        });

    }

    public void detectCone() {
        telemetry.addData("color: ", pipeline.getHue());
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
