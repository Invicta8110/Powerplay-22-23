package org.firstinspires.ftc.team8110_Invicta.OpModes.zOld.Auton;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team8110_Invicta.Hardware.ElefanteMcNuggets;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.Webcam;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Pipelines.ColorDetector;
import org.firstinspires.ftc.team8110_Invicta.Hardware.States.Colors;
import org.firstinspires.ftc.team8110_Invicta.Resources.RoadRunnerQuickstart.trajectorysequence.TrajectorySequence;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous
public class RedLeft extends LinearOpMode {
    ElefanteMcNuggets robot;
    ColorDetector pipeline;
    OpenCvCamera camera;

    @Override
    public void runOpMode() {
        pipeline = new ColorDetector();
        robot = new ElefanteMcNuggets(hardwareMap);
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

        TrajectorySequence step1 = robot.trajectorySequenceBuilder(new Pose2d(34,-58, Math.toRadians(87))).setTangent(Math.toRadians(87))
            .lineToSplineHeading(new Pose2d(24.4,-3.2, Math.toRadians(95)))
            .lineToSplineHeading(new Pose2d(66.8,-11.6, Math.toRadians(-9)))
            .lineToSplineHeading(new Pose2d(28,-1.2, Math.toRadians(168)))
            .lineToSplineHeading(new Pose2d(61.6,-34.8, Math.toRadians(-39)))
            .build();
        robot.followTrajectorySequence(step1);
        robot.getLift().goToHigh();
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