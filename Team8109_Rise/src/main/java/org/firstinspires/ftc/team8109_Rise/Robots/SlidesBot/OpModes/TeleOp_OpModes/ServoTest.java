package org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.OpModes.TeleOp_OpModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Claw;
import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.ServoIntakeArm;

@TeleOp
public class ServoTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
//        ServoIntakeArm arm = new ServoIntakeArm(gamepad1, telemetry, hardwareMap);

        Claw claw = new Claw(gamepad1, telemetry, hardwareMap);
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        telemetry.addLine("Waiting for start");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
//            arm.PositionTest();
//            arm.setTelemetry();

            claw.toggleClaw();
//            claw.setPosition();
            claw.setTelemetry();

            telemetry.update();
        }
    }
}
