package org.firstinspires.ftc.team8110_Invicta.OpModes.TeleOp_OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.team8110_Invicta.Control.TeleOp.StraferChassisTeleOp_Control;
import org.firstinspires.ftc.team8110_Invicta.Hardware.ScissorLift;

@TeleOp(name="ScissorLift TeleOp",group="Your Moms")
public class ScissorLiftTeleOP extends LinearOpMode {

    @Override
    public void runOpMode() {

        StraferChassisTeleOp_Control control = new StraferChassisTeleOp_Control("frontLeft", "frontRight", "backRight",
                "backLeft", hardwareMap, gamepad1, telemetry);
        ScissorLift lift = new ScissorLift("liftServo", hardwareMap);

        Gamepad gamepad1 = new Gamepad();

        telemetry.addLine("Waiting for start");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()){
            control.Drive();
            lift.alterPosition(gamepad1.left_stick_y);

            telemetry.addData("Lift Position", lift.getPosition());
            telemetry.update();
        }
    }
}