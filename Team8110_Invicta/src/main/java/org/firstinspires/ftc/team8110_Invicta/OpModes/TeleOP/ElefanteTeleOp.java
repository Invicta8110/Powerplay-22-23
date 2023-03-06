package org.firstinspires.ftc.team8110_Invicta.OpModes.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.team8110_Invicta.Hardware.ElefanteDrugMcNuggets;

@TeleOp
public class ElefanteTeleOp extends LinearOpMode {
    ElefanteDrugMcNuggets robot;

    ElefanteDrugMcNuggets.ConeClaw claw;

    public void runOpMode() {
        robot = new ElefanteDrugMcNuggets(hardwareMap);
        robot.getLift().reverse();

        telemetry.addData("status", "initialized");
        telemetry.update();

        waitForStart();


        while (opModeIsActive()) {
            telemetry.addData("status", "running");
            telemetry.addData("controller", gamepad1.toString());
            telemetry.addData("lift", robot.getLift().getPosition());
            telemetry.addData("claw", robot.getClaw().getPosition());
            telemetry.addData("speed", robot.getSpeed());
            telemetry.update();

            robot.teleOpClaw(gamepad1);
            robot.teleOpDrive(gamepad1);
            robot.teleOpLift(gamepad1);
        }
    }
}
