package org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.OpModes.Testing;

import android.os.Looper;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.team8109_Rise.Sensors.Camera.OpenCV.VisionPipelines.ColorPipeline;
import org.firstinspires.ftc.team8109_Rise.Sensors.Camera.OpenCV.VisionPipelines.MachineLearning;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous
public class MachineLearningTest_OpenCv extends LinearOpMode {
    OpenCvCamera camera; //TODO: Improve tracking
    MachineLearning pipeline;

    @Override
    public void runOpMode() throws InterruptedException {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        pipeline = new MachineLearning();

        camera.setPipeline(pipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened()
            {
                camera.startStreaming(320,240, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });

        while (opModeInInit()){
            telemetry.update();
        }
    }
}
