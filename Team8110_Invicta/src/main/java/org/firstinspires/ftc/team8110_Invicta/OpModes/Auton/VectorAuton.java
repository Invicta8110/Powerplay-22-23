package org.firstinspires.ftc.team8110_Invicta.OpModes.Auton;

import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.Webcam;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Pipelines.ColorPipeline;
import org.firstinspires.ftc.team8110_Invicta.Hardware.States.Colors;
import org.firstinspires.ftc.team8110_Invicta.Hardware.StormyMcNuggets;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous(name="VECTOR AUTON", preselectTeleOp="LIZ TELEOP")
public class VectorAuton extends LinearOpMode {
    OpenCvCamera camera;
    ColorPipeline pipeline;
    StormyMcNuggets robot;

    public void runOpMode() {
        robot = new StormyMcNuggets(hardwareMap);
        camera = new Webcam(hardwareMap, "webcam").getCamera();

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
                        .lineTo(new Vector2d(30, 20))
                        .build();
                break;
            case GREEN:
                trajectory2 = robot.trajectoryBuilder(initialPose)
                        .lineTo(new Vector2d(30, 24))
                        .build();
                break;
            case BLUE:
                trajectory2 = robot.trajectoryBuilder(initialPose)
                        .lineTo(new Vector2d(30, -20))
                        .build();
                break;
            default:
                trajectory2 = robot.trajectoryBuilder(initialPose)
                        .lineTo(new Vector2d(30, 24))
                        .build();
                break;
        }

        robot.followTrajectory(trajectory2);


    }
}


