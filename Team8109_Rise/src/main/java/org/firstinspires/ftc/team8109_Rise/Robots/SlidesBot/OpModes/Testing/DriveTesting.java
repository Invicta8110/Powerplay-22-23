package org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.OpModes.Testing;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms.Chassis;
import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms.OdoRetract;

@TeleOp
//@Disabled
public class DriveTesting extends LinearOpMode {

    public ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        Chassis chassis = new Chassis(gamepad1, telemetry, hardwareMap);
        OdoRetract odoRetract = new OdoRetract(gamepad1, hardwareMap);

        telemetry.addLine("Waiting For Start");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()){
            chassis.fieldCentricTest();
            odoRetract.toggleState();
            chassis.update();

            telemetry.addData("Pose Estimate", chassis.getPoseEstimate().getX());
            telemetry.addData("Getting Chassis Pose", chassis.getPoseVector());
            telemetry.addData("time", runtime.seconds());
            telemetry.update();
        }
    }
}
