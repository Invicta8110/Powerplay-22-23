package org.firstinspires.ftc.team8110_Invicta.OpModes.TeleOp_OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team8110_Invicta.Control.TeleOp.StraferChassisTeleOp_Control;
import org.firstinspires.ftc.team8110_Invicta.Hardware.StormyMcNuggets;

@TeleOp(name="Mecanum TeleOp",group="Your Moms")
public class SMcNTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() {
        StormyMcNuggets robot = new StormyMcNuggets(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            robot.Drive();
        }

    }
}
