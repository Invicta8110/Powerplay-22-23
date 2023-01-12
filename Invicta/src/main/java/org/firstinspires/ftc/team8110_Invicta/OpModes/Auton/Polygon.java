package org.firstinspires.ftc.team8110_Invicta.OpModes.Auton;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.team8110_Invicta.Hardware.Drivetrains.StraferChassis;

@Disabled
@Autonomous(name="Square Drive", group="Your Moms")
public class Polygon extends LinearOpMode {
    StraferChassis straferChassis;
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
            trajectory = straferChassis.trajectoryBuilder(globalPose).forward(sideLength).build();
            straferChassis.followTrajectory(trajectory);
            straferChassis.turn(Math.toRadians(90));
        }
    }

    public void init(String flName, String frName, String blName, String brName, HardwareMap hardwareMap) {
        straferChassis = new StraferChassis(flName, frName, brName, blName, hardwareMap);

        globalPose = new Pose2d(0, 0, 0);

        straferChassis.setPoseEstimate(globalPose);
    }
}
