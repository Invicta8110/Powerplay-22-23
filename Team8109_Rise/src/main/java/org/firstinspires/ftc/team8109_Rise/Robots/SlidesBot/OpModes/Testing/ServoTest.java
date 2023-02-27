package org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.OpModes.Testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms.Claw;
import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms.OdoRetract;
import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms.ServoIntakeArm;
import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms.Wrist;

@TeleOp
public class ServoTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
//        ServoIntakeArm arm = new ServoIntakeArm(gamepad1, telemetry, hardwareMap);

//        Claw claw = new Claw(gamepad1, telemetry, hardwareMap);
//        Wrist wrist = new Wrist(gamepad1, hardwareMap);

        OdoRetract retract = new OdoRetract(gamepad1, hardwareMap);
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        telemetry.addLine("Waiting for start");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
//            arm.togglePosition();
//            arm.setAngleIndividual();
//            arm.setTelemetry();

            retract.toggleState();
//            wrist.setPosition();

//            claw.toggleClaw();
//            claw.setPosition();
//            claw.setTelemetry();

            telemetry.addData("podState", retract.podState);
            telemetry.update();
        }
    }
}
