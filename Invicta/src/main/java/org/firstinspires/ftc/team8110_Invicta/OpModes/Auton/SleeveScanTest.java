package org.firstinspires.ftc.team8110_Invicta.OpModes.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Pipelines.ColorPipeline;
import org.firstinspires.ftc.team8110_Invicta.Hardware.States.Colors;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

@Autonomous(name="Camera Stream")
public class SleeveScanTest extends LinearOpMode {
    OpenCvWebcam camera;
    ColorPipeline pipeline ;

    public void runOpMode() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "webcam"), cameraMonitorViewId);

        camera.setPipeline(pipeline);
        pipeline = new ColorPipeline();

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {

            @Override
            public void onOpened() {
                camera.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {
                telemetry.addLine("Error Opening Camera");
                telemetry.addData("ErrorCode: ", errorCode);
                telemetry.update();
            }
        });

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Hue", pipeline.getHue());
            telemetry.addData("Color", pipeline.findColor());
            telemetry.update();
        }

        Colors sleeve = pipeline.findColor();

        switch (sleeve) {
            //TODO: fill this out
            case RED:
                //do red stuff
                break;
            case GREEN:
                //do green stuff
                break;
            case BLUE:
                //do blue stuff
                break;
            case UNKNOWN:
                //do unknown stuff
                break;
        }


    }
}


