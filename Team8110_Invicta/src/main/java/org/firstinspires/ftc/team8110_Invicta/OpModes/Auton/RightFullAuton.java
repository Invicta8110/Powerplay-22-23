package org.firstinspires.ftc.team8110_Invicta.OpModes.Auton;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Pipelines.ColorPipeline;
import org.firstinspires.ftc.team8110_Invicta.Hardware.States.Colors;
import org.firstinspires.ftc.team8110_Invicta.Hardware.StormyMcNuggets;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

@Autonomous(name="BROKEN AUTON", preselectTeleOp="LIZ TELEOP")
public class RightFullAuton extends LinearOpMode {
    OpenCvWebcam camera;
    ColorPipeline pipeline;
    StormyMcNuggets robot;

    public void runOpMode() {
        robot = new StormyMcNuggets(hardwareMap);
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

        telemetry.addData("Hue", pipeline.getHue());
        telemetry.addData("Color", pipeline.findColor());
        telemetry.update();

        Colors sleeve = pipeline.findColor();
        Pose2d initialPose = new Pose2d(0, 0, 0);

        Trajectory trajectory2;
        switch (sleeve) {
            //TODO: fill this out
            case RED:
                trajectory2 = robot.trajectoryBuilder(initialPose)
                        .forward(12)
                        .strafeLeft(24)
                        .build();
                break;
            case GREEN:
                trajectory2 = robot.trajectoryBuilder(initialPose)
                        .forward(12)
                        .build();
                break;
            case BLUE:
                trajectory2 = robot.trajectoryBuilder(initialPose)
                        .forward(12)
                        .strafeLeft(24)
                        .build();
                break;
            default:
                trajectory2 = robot.trajectoryBuilder(initialPose)
                        .forward(12)
                        .strafeLeft(24)
                        .build();
                break;
        }

        robot.followTrajectory(trajectory2);


    }
}


