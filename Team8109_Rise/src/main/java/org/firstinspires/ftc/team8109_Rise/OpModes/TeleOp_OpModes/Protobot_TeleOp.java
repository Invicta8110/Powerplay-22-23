package org.firstinspires.ftc.team8109_Rise.OpModes.TeleOp_OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team8109_Rise.Control.TeleOp.DRv4B_Test_Control;

@TeleOp
public class Protobot_TeleOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        DRv4B_Test_Control control = new DRv4B_Test_Control("drv4b_left", "drv4b_right", telemetry, gamepad1, hardwareMap);
        telemetry.addLine("Waiting for start");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()){
            control.DoubleReverse4Bar();
            control.Telemetry();
        }
    }
}
