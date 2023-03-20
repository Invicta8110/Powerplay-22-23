package org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.OpModes.Testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.team8109_Rise.Hardware.Arms.ServoArm;
import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms.Claw;
import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms.OdoRetract;
import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms.ServoIntakeArm;
import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms.Wrist;

@TeleOp
public class ServoTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
//        ServoIntakeArm arm = new ServoIntakeArm(gamepad1, telemetry, hardwareMap);
//        Servo armServo1 = hardwareMap.get(Servo.class, "armLeft");
        Claw claw = new Claw(gamepad1, telemetry, hardwareMap);
//        Wrist wrist = new Wrist(gamepad1, hardwareMap);

//        OdoRetract retract = new OdoRetract(gamepad1, hardwareMap);
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());


        telemetry.addLine("Waiting for start");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
//            arm.togglePosition();
//            arm.setAngleIndividual();
//            arm.setTelemetry();

//            retract.toggleState();
//            retract.setPodPosition();
//            wrist.setPosition();

            claw.toggleClaw();
            claw.setPosition();
            claw.setTelemetry();


            //TODO: revermosempm

//            if (gamepad1.a){
////                arm.armServo1.setPosition(60);
//                setAngle(armServo1, 60);
//            }
//
//            if (gamepad1.b){
//                setAngle(armServo1, 256);
//            }

//
//            telemetry.addData("podState", retract.podState);
//            telemetry.update();
        }
    }

    public void setAngle(Servo servo, double angle){
        servo.setPosition(angle/300);
    }
}
