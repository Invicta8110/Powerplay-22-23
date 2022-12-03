package org.firstinspires.ftc.team8110_Invicta.Hardware;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Drivetrains.StraferChassis;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.ScissorLift;

public class StormyMcNuggets {
    public StraferChassis straferChassis;
    public ScissorLift lift;
    public Gamepad gamepad1;
    protected double[] positions[];

    public StormyMcNuggets(HardwareMap hwmap) {
        straferChassis = new StraferChassis(hwmap);
        lift = new ScissorLift(hwmap);
    }

    public StormyMcNuggets(StraferChassis straferChassis, ScissorLift scissorLift) {
        this.straferChassis = straferChassis;
        this.lift = scissorLift;
    }

    /**
     * Drives the robot during the teleop phase!!
     */
    public void teleOpDrive() {
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
        straferChassis.setPower(fLeft, fRight, bRight, bLeft);
    }
}
