package org.firstinspires.ftc.team8110_Invicta.OpModes.Auton;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.Webcam;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Pipelines.SignalIdentifier;
import org.firstinspires.ftc.team8110_Invicta.Hardware.States.Colors;
import org.firstinspires.ftc.team8110_Invicta.Hardware.StormyMcNuggets;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous(name="STRAFE AUTON", preselectTeleOp="LIZ TELEOP")
public class StrafeAuton extends LinearOpMode {
    OpenCvCamera camera;
    SignalIdentifier pipeline;
    StormyMcNuggets robot;

    public void runOpMode() {
        robot = new StormyMcNuggets(hardwareMap);
        camera = new Webcam(hardwareMap, "webcam").getCamera();
        pipeline = new SignalIdentifier(telemetry);

        camera.setPipeline(pipeline);

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

        while (opModeInInit()){
            if (!pipeline.TargetRect.empty()){
                telemetry.addData("Parking Zone", pipeline.signalPosition);
            }

            telemetry.addLine("Waiting for start");
            telemetry.update();
        }

        waitForStart();

        SignalIdentifier.SignalPosition sleeve = pipeline.readSignal();

        Pose2d initialPose = new Pose2d(0, 0, 0);
        Trajectory forward = robot.trajectoryBuilder(initialPose)
                .forward(20).build();
        robot.followTrajectory(forward);

        Pose2d middlePose = forward.end();

        Trajectory trajectory2;
        switch (sleeve) {
            //TODO: fill this out
            case LEFT:
                trajectory2 = robot.trajectoryBuilder(middlePose)
                        .strafeLeft(20)
                        .build();
                break;
            case MIDDLE:
                trajectory2 = robot.trajectoryBuilder(middlePose)
                        .build();
                break;
            case RIGHT:
                trajectory2 = robot.trajectoryBuilder(middlePose)
                        .strafeRight(20)
                        .build();
            default:
                trajectory2 = robot.trajectoryBuilder(middlePose)
                        .strafeLeft(20)
                        .build();
                break;
        }

        robot.followTrajectory(trajectory2);


    }
}


