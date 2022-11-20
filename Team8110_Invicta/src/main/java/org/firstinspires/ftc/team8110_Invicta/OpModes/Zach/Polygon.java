package org.firstinspires.ftc.team8110_Invicta.OpModes.Zach;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.team8110_Invicta.Hardware.BotMechanisms.Drivetrains.DriveTrain;

@Autonomous(name="Square Drive", group="Your Moms")
public class Polygon extends LinearOpMode {
    DriveTrain driveTrain;
    Trajectory trajectory;
    Pose2d globalPose;

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        driveSquare(40);
    }

    public void driveSquare(int sideLength) {
        init("frontLeft","frontRight","backLeft","backRight", hardwareMap);

        for (int i = 0; i < 4; i++) {
            trajectory = driveTrain.trajectoryBuilder(globalPose).forward(sideLength).build();
            driveTrain.followTrajectory(trajectory);
            driveTrain.turn(Math.toRadians(90));
        }
    }

    public void init(String flName, String frName, String blName, String brName, HardwareMap hardwareMap) {
        driveTrain = new DriveTrain(flName, frName, brName, blName, hardwareMap);

        globalPose = new Pose2d(0, 0, 0);

        driveTrain.setPoseEstimate(globalPose);
    }
}
