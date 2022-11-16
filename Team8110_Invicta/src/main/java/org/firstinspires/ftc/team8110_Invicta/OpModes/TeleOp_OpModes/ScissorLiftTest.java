package org.firstinspires.ftc.team8110_Invicta.OpModes.TeleOp_OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team8110_Invicta.Hardware.ScissorLift;

@TeleOp(name = "ScissorLift Test", group = "Your Moms")
public class ScissorLiftTest extends LinearOpMode {
    private ScissorLift lift;

    @Override
    public void runOpMode() {
        lift = new ScissorLift(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.left_trigger > 0) {
                lift.move(-gamepad1.left_trigger);
            } else if (gamepad1.right_trigger > 0) {
                lift.move(gamepad1.right_trigger);
            } else {
                lift.move(0);
            }

            telemetry.addData("Lift Position", lift.getPosition());
            telemetry.update();


        }
    }
}
