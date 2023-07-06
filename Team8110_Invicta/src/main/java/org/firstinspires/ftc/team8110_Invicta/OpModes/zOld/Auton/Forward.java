package org.firstinspires.ftc.team8110_Invicta.OpModes.zOld.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team8110_Invicta.Hardware.ElefanteMcNuggets;

@Autonomous
public class Forward extends LinearOpMode {
    ElefanteMcNuggets robot;

    @Override
    public void runOpMode() {
        robot = new ElefanteMcNuggets(hardwareMap);

        waitForStart();

        telemetry.addData("Start", robot.getWheelPositions());
        telemetry.update();

        robot.runToPosition(12);

        telemetry.addData("End", robot.getWheelPositions());
        telemetry.update();
    }
}
