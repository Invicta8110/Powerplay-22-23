package org.firstinspires.ftc.team8110_Invicta.OpModes.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team8110_Invicta.Control.StormyMcNuggets;

@TeleOp(name="Mecanum TeleOp",group="Your Moms")
public class MecanumDrive extends LinearOpMode {

    @Override
    public void runOpMode() {
        StormyMcNuggets robot = new StormyMcNuggets(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        robot.direct(0.1);

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.addData("Lift Position", robot.getLiftPosition());
            telemetry.addData("Lift Height", robot.getLiftHeight());
            telemetry.addData("Claw State", robot.position);
            telemetry.addData("Claw Position", robot.claw.getPosition());
            telemetry.addData("Motor Positions", robot.getWheelPositions());
            telemetry.update();
            robot.teleOpDrive(gamepad1);
            robot.teleOpLift(gamepad1);
            robot.teleOpClaw(gamepad1);
        }

    }
}
