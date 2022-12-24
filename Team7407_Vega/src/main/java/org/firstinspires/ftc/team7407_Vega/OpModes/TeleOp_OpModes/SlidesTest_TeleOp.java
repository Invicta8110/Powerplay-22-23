package org.firstinspires.ftc.team7407_Vega.OpModes.TeleOp_OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team7407_Vega.Control.TeleOp.SlidesTest;

@TeleOp
public class SlidesTest_TeleOp extends LinearOpMode {

    @Override
    public void runOpMode() {
        SlidesTest control = new SlidesTest(gamepad1, telemetry, hardwareMap);
        telemetry.addLine("Waiting for start");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()){
            control.SlidesControl();
            control.Telemetry();
        }
    }
}
