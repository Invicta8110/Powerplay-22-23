package org.firstinspires.ftc.team8110_Invicta.OpModes.Auton;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team8110_Invicta.Hardware.ElefanteDrugMcNuggets;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.Webcam;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Pipelines.ColorDetector;
import org.firstinspires.ftc.team8110_Invicta.Hardware.States.Colors;
import org.firstinspires.ftc.team8110_Invicta.Resources.RoadRunnerQuickstart.trajectorysequence.TrajectorySequence;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.Vector;

@Autonomous
public class RedLeft extends LinearOpMode {
    ElefanteDrugMcNuggets robot;
    ColorDetector pipeline;
    OpenCvCamera camera;

    @Override
    public void runOpMode() {
        pipeline = new ColorDetector();
        robot = new ElefanteDrugMcNuggets(hardwareMap);
        camera = new Webcam(hardwareMap).getCamera();

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

        Colors color = pipeline.getColor();

        TrajectorySequence step1 = robot.trajectorySequenceBuilder(new Pose2d(0, 0, 0)).forward(36).turn(Math.toRadians(45)).build();
        robot.followTrajectorySequence(step1);
        robot.getLift().upLevel();
        robot.getClaw().open();
        Pose2d endPose = robot.getPoseEstimate();
        TrajectorySequence park;

        switch (color) {
            case RED: {
                park = robot.trajectorySequenceBuilder(endPose).lineTo(new Vector2d(-6, -18)).build();
                break;
            } case BLUE: {
                park = robot.trajectorySequenceBuilder(endPose).lineTo(new Vector2d(-6, -12)).build();
                break;
            } case GREEN: {
            } default: {
                park = robot.trajectorySequenceBuilder(endPose).lineTo(new Vector2d(6, 6)).build();
                break;
            }
        }
        robot.followTrajectorySequence(park);
    }
}

/* STEPS
1. drive forward to high junction (36 in)
2. turn 45 degrees right
3. drive forward 6 in
4. lift scissor lift
5. deposit claw
 */