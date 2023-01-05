package org.firstinspires.ftc.team8110_Invicta.OpModes.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team8110_Invicta.Control.StormyMcNuggets;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.ScissorLift;

@TeleOp(name = "ScissorLift Test", group = "Your Moms")
public class ScissorLiftTest extends LinearOpMode {
    StormyMcNuggets robot;

    @Override
    public void runOpMode() {;
        robot = new StormyMcNuggets(hardwareMap);
        //lift = robot.lift;

        waitForStart();

        while (opModeIsActive()) {
            robot.teleOpLift(gamepad1);

            telemetry.addData("Lift Position", robot.lift.getPosition());
            //telemetry.addData("Lift Height", robot.lift.getInches());
            telemetry.update();
        }
    }
}
