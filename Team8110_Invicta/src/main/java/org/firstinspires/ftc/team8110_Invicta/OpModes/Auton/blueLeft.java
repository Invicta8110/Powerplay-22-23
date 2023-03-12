package org.firstinspires.ftc.team8110_Invicta.OpModes.Auton;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team8110_Invicta.Hardware.ElefanteDrugMcNuggets;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Pipelines.ColorDetector;

@Config
@Autonomous


public class blueLeft extends LinearOpMode {

//    InertialMeasurementUnit imu;
  //  public InertialMeasurementUnit imu = new InertialMeasurementUnit(hardwareMap);


    @Override
    public void runOpMode() {

//        InertialMeasurementUnit imu = new InertialMeasurementUnit(hardwareMap);
        ElefanteDrugMcNuggets robot = new ElefanteDrugMcNuggets(hardwareMap);

        Trajectory example = robot.trajectoryBuilder(new Pose2d())
                .forward(24.0)
                .build();

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            robot.followTrajectory(example);
        }
    }
}
