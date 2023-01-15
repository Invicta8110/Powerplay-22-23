package org.firstinspires.ftc.team8110_Invicta.Control.MotionControl;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.firstinspires.ftc.team8110_Invicta.Hardware.Drivetrains.StraferChassis;

public class SplineControl {
    StraferChassis robot;
    Pose2d pose;

    public SplineControl(StraferChassis robot, Pose2d initialPose) {
        this.robot = robot;
        this.pose = initialPose;
    }

    public static void splineTo(Vector2d target) {

    }
}
