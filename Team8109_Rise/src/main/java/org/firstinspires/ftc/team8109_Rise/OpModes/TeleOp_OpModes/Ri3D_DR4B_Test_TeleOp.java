package org.firstinspires.ftc.team8109_Rise.OpModes.TeleOp_OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team8109_Rise.Control.TeleOp.Ri3D_DR4B_Test_Control;

@TeleOp
public class Ri3D_DR4B_Test_TeleOp extends LinearOpMode {

    @Override
    public void runOpMode() {

        Ri3D_DR4B_Test_Control test_control = new Ri3D_DR4B_Test_Control("bar", telemetry, gamepad1, hardwareMap);

        telemetry.addLine("Waiting for start");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()){
            test_control.DoubleReverse4Bar_Motion();
            test_control.Telemetry();
            telemetry.update();
        }
    }
}
