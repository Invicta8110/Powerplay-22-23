package org.firstinspires.ftc.team8110_Invicta.Hardware;

import com.acmerobotics.roadrunner.drive.Drive;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.team8110_Invicta.Hardware.BotMechanisms.Drivetrains.DriveTrain;

public class StormyMcNuggets {
    public DriveTrain driveTrain;
    public ScissorLift lift;
    public Gamepad gamepad1;

    public StormyMcNuggets(HardwareMap hwmap) {
        driveTrain = new DriveTrain(hwmap);
        lift = new ScissorLift(hwmap);
    }

    public StormyMcNuggets(DriveTrain driveTrain, ScissorLift scissorLift) {
        this.driveTrain = driveTrain;
        this.lift = scissorLift;
    }

    public void Drive(){
        double drive = Math.pow(gamepad1.left_stick_y, 3); //Between -1 and 1
        double turn = Math.pow(gamepad1.right_stick_x, 3);
        double strafe = Math.pow(gamepad1.left_stick_x, 3);

        // Mecanum Drive Calculations
        double fLeft = 0.875 * drive - 1 * strafe - 0.8 * turn;
        double fRight = 0.875 * drive + 1 * strafe + 0.8 * turn;
        double bRight = -0.875 * drive - 1 * strafe + 0.8 * turn;
        double bLeft = -0.875 * drive + 1 * strafe - 0.8 * turn;

        // This ensures that the power values the motors are set to are in the range (-1, 1)
        double max = Math.max(Math.max(Math.abs(fLeft), Math.abs(fRight)), Math.max(Math.abs(bLeft), Math.abs(bRight)));
        if (max > 1.0) {
            fLeft /= max;
            fRight /= max;
            bLeft /= max;
            bRight /= max;
        }

        driveTrain.setPower(fLeft, fRight, bRight, bLeft);
    }
}
