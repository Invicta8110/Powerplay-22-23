package org.firstinspires.ftc.team8110_Invicta.OpModes.Zach;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.team8110_Invicta.Hardware.BotMechanisms.Drivetrains.MecanumDriveTrain;

@Autonomous(name="Square Drive", group="Your Moms")
public class Polygon extends LinearOpMode {
    static MecanumDriveTrain driveTrain;
    private static HardwareMap hardwareMap;
    static Trajectory trajectory;

    @Override
    public void runOpMode() throws InterruptedException {
        driveSquare(20);
    }
//j
    public static void driveSquare(int sideLength) {
        init("frontLeft","frontRight","backLeft","backRight", hardwareMap);

        for (int i = 0; i < 4; i++) {
            setTrajectory(sideLength, new Pose2d(0, 0, 0));
            driveTrain.followTrajectoryAsync(trajectory);
            driveTrain.turnAsync(Math.toRadians(90));
        }
    }

    public static void init(String flName, String frName, String blName, String brName, HardwareMap hardwareMap) {
        driveTrain = new MecanumDriveTrain(flName, frName, brName, blName, hardwareMap);

        Pose2d globalPose = new Pose2d(0,0,0);

        driveTrain.setPoseEstimate(globalPose);
    }

    public static void setTrajectory(int distance, Pose2d globalPose) {
        trajectory = driveTrain.trajectoryBuilder(globalPose).forward(distance).build();
    }
}
