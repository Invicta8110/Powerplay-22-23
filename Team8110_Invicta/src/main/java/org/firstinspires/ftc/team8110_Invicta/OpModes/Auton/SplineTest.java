package org.firstinspires.ftc.team8110_Invicta.OpModes.Auton;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team8110_Invicta.Hardware.StormyMcNuggets;

@Disabled
@Autonomous(name="To and Fro", group="Your Moms")
public class SplineTest extends LinearOpMode {
    StormyMcNuggets robot;
    Trajectory spline, line;

    @Override
    public void runOpMode() {
        robot = new StormyMcNuggets(hardwareMap);

        waitForStart();

        Pose2d globalPose = new Pose2d(0, 0, 0);
        robot.setPoseEstimate(globalPose);

        spline = robot.trajectoryBuilder(globalPose).splineTo(new Vector2d(24, 24), 0).build();
        robot.followTrajectory(spline);

        globalPose = new Pose2d(0, 0, 0);
        robot.setPoseEstimate(globalPose);

        line = robot.trajectoryBuilder(globalPose).lineTo(new Vector2d(-24, -24)).build();
        robot.followTrajectory(line);
    }
}
