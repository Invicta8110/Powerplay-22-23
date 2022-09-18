package org.firstinspires.ftc.workshop_Code.OpModes.TeleOp_OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.workshop_Code.Control.TeleOp.Chain_TeleOp_Control;

@TeleOp
public class chainTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() {
        Chain_TeleOp_Control teleOp = new Chain_TeleOp_Control("chainMotor", hardwareMap, telemetry);

        telemetry.addLine("Waiting for start");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()){

        }
    }
}
