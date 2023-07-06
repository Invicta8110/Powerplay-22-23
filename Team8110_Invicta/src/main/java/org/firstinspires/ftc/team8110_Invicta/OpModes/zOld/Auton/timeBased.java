package org.firstinspires.ftc.team8110_Invicta.OpModes.zOld.Auton;

import static android.os.SystemClock.sleep;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team8110_Invicta.Hardware.ElefanteMcNuggets;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.Webcam;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Pipelines.ColorDetector;
import org.firstinspires.ftc.team8110_Invicta.Hardware.States.Colors;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous
public class timeBased extends LinearOpMode {
    ElefanteMcNuggets robot;
    ColorDetector pipeline;
    OpenCvCamera camera;

    @Override
    public void runOpMode() {
        robot = new ElefanteMcNuggets(hardwareMap);
        robot.frontRight.setDirectionReverse();
        robot.backRight.setDirectionReverse();
        camera = new Webcam(hardwareMap).getCamera();
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

        waitForStart();
        robot.setDrivePower(0.8,0,0);
        sleep(500);
        robot.setDrivePower(0, 0, 0);
        sleep(1000);

        Colors color = pipeline.getColor();

        //fLeft, fRight, bRight, bLeft
        switch (color) {
            case RED:
                robot.setDrivePower(0.8,0,0);
                sleep(1500);
                robot.setDrivePower(0, 0, 0);
                sleep(500);
//                // what is the intended motion here
//                robot.setPower(-.8, .8, -.8, .8);
//                sleep(1000);
//                robot.setPower(0, 0, 0, 0);
//                sleep(500);
                break;
            case GREEN:
                robot.setDrivePower(.8,0,0);
                sleep(500);
                robot.setDrivePower(0, 0, 0);
                sleep(500);
                break;
            case BLUE:
                robot.setDrivePower(-.8,0,0);
                sleep(1000);
                robot.setDrivePower(0, 0, 0);
                sleep(500);
//                robot.setPower(.8, -.8, .8, -.8);
//                sleep(1000);
//                robot.setPower(0, 0, 0, 0);
//                sleep(500);
                break;
        }
    }
}
