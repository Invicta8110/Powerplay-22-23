package org.firstinspires.ftc.team8110_Invicta.OpModes.Auton;

import android.text.style.TtsSpan;

import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.acmerobotics.roadrunner.util.NanoClock;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.team8110_Invicta.Hardware.ElefanteDrugMcNuggets;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Pipelines.ColorDetector;
import org.firstinspires.ftc.team8110_Invicta.Hardware.States.Colors;
import org.firstinspires.ftc.team8110_Invicta.Resources.RoadRunnerQuickstart.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.team8110_Invicta.Resources.RoadRunnerQuickstart.trajectorysequence.TrajectorySequenceBuilder;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;


@Autonomous
public class LeftBlue extends LinearOpMode {

    ElefanteDrugMcNuggets robot;

    Color_Control dog;

    HardwareMap hardwareMap;

    ColorDetector cat;

    ElapsedTime seconds;

    OpenCvCamera camera;

    TrajectorySequence build;

    Pose2d pos;

    ElefanteDrugMcNuggets.ConeClaw sup;

    ElefanteDrugMcNuggets.ScissorLift yea;

    Vector2d vector;



    @Override
    public void runOpMode() throws InterruptedException {

        robot = new ElefanteDrugMcNuggets(hardwareMap);
        cat = new ColorDetector();
       // pos = new Pose2d(34,60,Math.toRadians(270.0));
        vector = new Vector2d(0,0);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Camera1"), cameraMonitorViewId);

        cat.getColor();

        if (getRuntime() > 25){
            sup.open();
            sup.close();
            yea.upLevel();
            build = new TrajectorySequenceBuilder(new Pose2d(34.4,60, Rotation2d.fromDegrees(-90.0)))
                    .strafeRight(21.3)
                    .forward(25.0)
                    .strafeLeft(10.8)
                    .build();
            sup.open();
            sup.close();
            yea.downLevel();
            while (opModeIsActive()) {
                build = new TrajectorySequenceBuilder(new Pose2d(23.6, 33.3, Rotation2d.fromDegrees(-90.0)))
                        .strafeRight(11.0)
                        .back(2.0)
                        .turn(Math.toRadians(180.0))
                        .forward(24.0)
                        .turn(Math.toRadians(45.0))
                        .forward(4.0)
                        .build();
                sup.open();
                sup.close();
                yea.upLevel();
                build = new TrajectorySequenceBuilder(new Pose2d(9.5, 62.0, Rotation2d.fromDegrees(-225.0)))
                        .back(4.0)
                        .turn(Math.toRadians(-45.0))
                        .turn(Math.toRadians(180.0))
                        .forward(25.0)
                        .strafeLeft(10.8)
                        .build();
                sup.open();
                sup.close();
            }
        }

        //last part for the robot
        if(getRuntime() > 25 && getRuntime() < 30){
            if(cat.getColor() == Colors.RED){
                //RED
                build = new TrajectorySequenceBuilder( new Pose2d(34.4, 60.0, Rotation2d.fromDegrees(-90.0)))

                        .strafeLeft(30.0)
                        .build();
            }
            if(cat.getColor() == Colors.BLUE){
                //BLUE
                build = new TrajectorySequenceBuilder(new Pose2d(34.4, 60.0, Rotation2d.fromDegrees(-90.0)))
                        .strafeRight(12.0)
                        .build();
            }
            if(cat.getColor() == Colors.GREEN){
                //GREEN
                build = new TrajectorySequenceBuilder(new Pose2d(34.4,60.0, Rotation2d.fromDegrees(-90.0)))
                        .strafeRight(12.0)
                        .forward(22.0)
                        .strafeLeft(24.0)
                        .build();
            }
        }

        waitForStart();


       // robot.followTrajectorySequence(build);
    }


}
