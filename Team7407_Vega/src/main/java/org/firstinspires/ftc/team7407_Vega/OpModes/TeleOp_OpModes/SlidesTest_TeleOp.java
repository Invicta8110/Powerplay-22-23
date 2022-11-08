package org.firstinspires.ftc.team7407_Vega.OpModes.TeleOp_OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team7407_Vega.Control.TeleOp.SlidesTest;

public class SlidesTest_TeleOp extends LinearOpMode {

    @Override
    public void runOpMode() {
        SlidesTest control = new SlidesTest("frontLeft", "frontRight", "backRight", "backLeft", gamepad1, telemetry, hardwareMap);
    }
}
