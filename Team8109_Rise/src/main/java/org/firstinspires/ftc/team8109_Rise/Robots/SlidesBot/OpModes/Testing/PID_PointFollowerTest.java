package org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.OpModes.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team8109_Rise.Math.Vectors.Vector3D;
import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms.Chassis;
import org.opencv.core.Mat;

@Autonomous
public class PID_PointFollowerTest extends LinearOpMode {

    Chassis chassis;
    Vector3D point = new Vector3D(40, 40, Math.PI/2);

    @Override
    public void runOpMode() throws InterruptedException {
        chassis = new Chassis(gamepad1, telemetry, hardwareMap);
        waitForStart();

        while (opModeIsActive()){
            chassis.update();
            chassis.updatePoseEstimate();
            chassis.goToPose(point);

            telemetry.addData("Pose", chassis.getPoseEstimate());

            telemetry.addData("HeadingPID error", chassis.HeadingPID.error);
            telemetry.addData("TranslationalX error", chassis.TranslationalPID_X.error);
            telemetry.addData("TranslationalY error", chassis.TranslationalPID_Y.error);
            telemetry.addData("Distance to Pose", point.findDistance(chassis.getPoseVector()));

            telemetry.addData("X PID Proportion", chassis.TranslationalPID_X.P);
            telemetry.addData("Y PID Proportion", chassis.TranslationalPID_Y.P);
            telemetry.addData("Heading PID Proportion", chassis.HeadingPID.P);

            telemetry.addData("TranslationalX PID Derivative", chassis.TranslationalPID_X.D);
            telemetry.addData("TranslationalY PID Derivative", chassis.TranslationalPID_Y.D);

            telemetry.addData("X PID Integral", chassis.TranslationalPID_X.I);
            telemetry.addData("Y PID Integral", chassis.TranslationalPID_Y.I);
            telemetry.addData("Heading PID Integral", chassis.HeadingPID.I);

            telemetry.update();
        }
    }
}
