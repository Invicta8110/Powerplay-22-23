
package org.firstinspires.ftc.team8110_Invicta.OpModes.Auton;

import android.text.style.TtsSpan;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.acmerobotics.roadrunner.util.NanoClock;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.team8110_Invicta.Hardware.ElefanteDrugMcNuggets;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.Webcam;
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
    public double num = 2500.0;




    @Override
    public void runOpMode() throws InterruptedException {

        robot = new ElefanteDrugMcNuggets(hardwareMap);
        cat = new ColorDetector();
        // pos = new Pose2d(34,60,Math.toRadians(270.0));
        vector = new Vector2d(0,0);

        camera = new Webcam(hardwareMap).getCamera();

        cat.getColor();

        //double num = 2500.0;

        robot.getLift().power(180.0);
        build = robot.trajectorySequenceBuilder(new Pose2d(34.4,60.0,Math.toRadians(-90.0)))
                        .forward(4.0)
                        .build();
        sup.open();
        robot.getLift().power(-160.0);
        sup.close();
        //yea.upLevel();
        robot.getLift().power(5200.0);
        build = robot.trajectorySequenceBuilder(new Pose2d(34.4,60, Math.toRadians(-90.0)))
                .strafeRight(21.3)
                .forward(25.0)
                .strafeLeft(10.8)
                .build();
        sup.open();
        sup.close();
        //yea.downLevel();
        //robot.getLift().power(-500.0);
        for(int i = 0; i < 6; i++) {
            build = robot.trajectorySequenceBuilder(new Pose2d(23.6, 33.3, Math.toRadians(-90.0)))
                    .strafeRight(11.0)
                    .back(2.0)
                    .turn(Math.toRadians(180.0))
                    .forward(24.0)
                    .turn(Math.toRadians(45.0))
                    .forward(4.0)
                    .build();
            robot.getLift().power(-1000.0);
            sup.open();
            sup.close();
            robot.getLift().power(5200.0);
            //yea.upLevel();
            robot.getLift().power(num);
            build = robot.trajectorySequenceBuilder(new Pose2d(9.5, 62.0, Math.toRadians(-225.0)))
                    .back(4.0)
                    .turn(Math.toRadians(-45.0))
                    .turn(Math.toRadians(180.0))
                    .forward(25.0)
                    .strafeLeft(10.8)
                    .build();
            sup.open();
            sup.close();
            num = num - 500.0;
        }

        //last part for the robot
//        if(cat.getColor() == Colors.RED){
//            //RED
//            build = robot.trajectorySequenceBuilder(new Pose2d(34.4, 60.0, Math.toRadians(-90.0)))
//                    .strafeLeft(30.0)
//                    .build();
//        }
//        if(cat.getColor() == Colors.BLUE){
//                //BLUE
//            build = robot.trajectorySequenceBuilder(new Pose2d(34.4, 60.0, Math.toRadians(-90.0)))
//                    .strafeRight(12.0)
//                    .build();
//        }
//        if(cat.getColor() == Colors.GREEN){
//            //GREEN
//            build = robot.trajectorySequenceBuilder(new Pose2d(34.4,60.0, Math.toRadians(-90.0)))
//                    .strafeRight(12.0)
//                    .forward(22.0)
//                    .strafeLeft(24.0)
//                    .build();
//        }


        waitForStart();

        robot.followTrajectorySequence(build);
    }


}