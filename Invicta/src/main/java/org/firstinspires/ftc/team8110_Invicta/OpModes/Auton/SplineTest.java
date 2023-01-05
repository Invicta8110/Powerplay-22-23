package org.firstinspires.ftc.team8110_Invicta.OpModes.Auton;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team8110_Invicta.Control.StormyMcNuggets;

public class SplineTest extends LinearOpMode {
    StormyMcNuggets robot;
    Trajectory trajectory;

    @Override
    public void runOpMode() {
        robot = new StormyMcNuggets(hardwareMap);
        Pose2d globalPose = new Pose2d(0, 0, 0);
        robot.setPoseEstimate(globalPose);

        trajectory = robot.trajectoryBuilder(globalPose).splineTo(new Vector2d(0, 0), 0).build();
    }
}
