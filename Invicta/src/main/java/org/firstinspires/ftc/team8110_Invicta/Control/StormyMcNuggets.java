package org.firstinspires.ftc.team8110_Invicta.Control;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Drivetrains.StraferChassis;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.Claw;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.ScissorLift;

import java.util.ArrayList;
import java.util.List;

public class StormyMcNuggets extends StraferChassis {
    public ScissorLift lift;
    public Claw claw;
    public List<Double> positions = new ArrayList<>();

    public StormyMcNuggets(HardwareMap hwmap) {
        super(hwmap);
        this.lift = new ScissorLift("lift", hwmap);
        this.claw = new Claw("claw", hwmap);
    }

    public StormyMcNuggets(HardwareMap hwmap, ScissorLift scissorLift, Claw claw) {
        super(hwmap);
        this.lift = scissorLift;
        this.claw = claw;
    }

    /**
     * Drives the robot during the teleop phase!!
     * @param gamepad the gamepad used to control the robot
     */
    public void teleOpDrive(Gamepad gamepad) {
        double drive = Math.pow(gamepad.left_stick_y, 3); //Between -1 and 1
        double turn = Math.pow(gamepad.right_stick_x, 3);
        double strafe = Math.pow(gamepad.left_stick_x, 3);

        // Mecanum Drive Calculations
        double fLeft = -0.875 * drive - 1 * strafe - 0.8 * turn;
        double fRight = -0.875 * drive + 1 * strafe + 0.8 * turn;
        double bRight = 0.875 * drive - 1 * strafe + 0.8 * turn;
        double bLeft = 0.875 * drive + 1 * strafe - 0.8 * turn;

        // This ensures that the power values the motors are set to are in the range (-1, 1)
        double max = Math.max(Math.max(Math.abs(fLeft), Math.abs(fRight)), Math.max(Math.abs(bLeft), Math.abs(bRight)));
        if (max > 1.0) {
            fLeft /= max;
            fRight /= max;
            bLeft /= max;
            bRight /= max;
        }
        this.setPower(fLeft, fRight, bRight, bLeft);
    }

    public void teleOpLift(Gamepad gamepad) {
        double down = gamepad.dpad_down ? 1 : 0;
        double up = gamepad.dpad_up ? 1 : 0;

        lift.move(up-down);
    }

    public double getLiftPosition() {
        return lift.getPosition();
    }

    public double getLiftHeight() {
        return lift.getInches();
    }

    public void teleOpClaw(Gamepad gamepad) {
        Claw.Position state = claw.getState();

        if (gamepad.a) {
            claw.open();
        } else if (gamepad.y) {
            claw.close();
        }
    }
}
