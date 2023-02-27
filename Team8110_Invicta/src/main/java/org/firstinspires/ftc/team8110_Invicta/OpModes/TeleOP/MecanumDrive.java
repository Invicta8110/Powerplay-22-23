package org.firstinspires.ftc.team8110_Invicta.OpModes.TeleOP;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team8110_Invicta.Hardware.StormyMcNuggets;

@TeleOp(name="LIZ TELEOP",group="Your Moms")
public class MecanumDrive extends LinearOpMode {

    @Override
    public void runOpMode() {
        StormyMcNuggets robot = new StormyMcNuggets(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.addData("Lift Position", robot.getLiftPosition());
            telemetry.addData("Lift Height", robot.getLiftHeight());
            telemetry.addData("Claw State", robot.position);
            telemetry.addData("Claw Position", robot.claw.getPosition());
            telemetry.addData("Motor Positions", robot.getWheelPositions());
            telemetry.update();

            robot.teleOpLift(gamepad1);
            //robot.teleOpClaw(gamepad1);

            robot.setWeightedDrivePower(
                    new Pose2d(
                            -gamepad1.left_stick_y/2,
                            -gamepad1.left_stick_x/2,
                            -gamepad1.right_stick_x/2
                    )
            );
        }

    }
}
