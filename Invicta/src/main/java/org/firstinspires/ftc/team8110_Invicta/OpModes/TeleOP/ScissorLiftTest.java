package org.firstinspires.ftc.team8110_Invicta.OpModes.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team8110_Invicta.Hardware.StormyMcNuggets;

@TeleOp(name = "ScissorLift Test", group = "Your Moms")
public class ScissorLiftTest extends LinearOpMode {
    StormyMcNuggets robot = new StormyMcNuggets(hardwareMap);

    @Override
    public void runOpMode() {;
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.left_trigger > 0) {
                robot.lift.move(-gamepad1.left_trigger);
            } else if (gamepad1.right_trigger > 0) {
                robot.lift.move(gamepad1.right_trigger);
            } else {
                robot.lift.move(0);
            }

            telemetry.addData("Lift Position", robot.lift.getPosition());
            telemetry.addData("Lift Height", robot.lift.getInches());
            telemetry.update();
        }
    }
}
