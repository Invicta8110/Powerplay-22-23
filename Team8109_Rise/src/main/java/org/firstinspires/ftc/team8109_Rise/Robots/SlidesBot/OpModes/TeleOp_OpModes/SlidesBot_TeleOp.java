package org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.OpModes.TeleOp_OpModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Chassis;
import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.ViperSlides;

@TeleOp
public class SlidesBot_TeleOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        ViperSlides slides = new ViperSlides(gamepad1, hardwareMap);
//        Chassis chassis = new Chassis(gamepad1, telemetry, hardwareMap);

        telemetry.addLine("Waiting For Start");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()){
            slides.setSlidePosition();
            telemetry.addData("slides height", slides.getHeight());
            telemetry.update();
        }
    }
}
