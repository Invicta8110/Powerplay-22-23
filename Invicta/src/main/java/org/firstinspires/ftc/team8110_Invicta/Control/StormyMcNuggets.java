package org.firstinspires.ftc.team8110_Invicta.Control;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team8110_Invicta.Hardware.Drivetrains.StraferChassis;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.Claw;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.Motor;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.ScissorLift;

public class StormyMcNuggets extends StraferChassis implements ScissorLift, Claw {
    public Motor lift;
    public Servo claw;
    public Position position;

    public StormyMcNuggets(HardwareMap hardwareMap, String liftName, String clawName) {
        super(hardwareMap);
        lift = new Motor(liftName, hardwareMap);
        lift.setDirectionReverse();

        claw = hardwareMap.servo.get(clawName);
    }

    public StormyMcNuggets(HardwareMap hardwareMap) {
        this(hardwareMap, "lift", "claw");
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

    public void teleOpClaw(Gamepad gamepad) {
        Claw.Position state = this.getState();

        if (gamepad.a) {
            this.open();
        } else if (gamepad.y) {
            this.close();
        }
    }

    public void teleOpLift(Gamepad gamepad) {
        double down = gamepad.dpad_down ? 1 : 0;
        double up = gamepad.dpad_up ? 1 : 0;

        this.move(up-down);
    }

    public double getLiftPosition() {
        return lift.getCurrentPosition();
    }

    public double getLiftHeight() {
        return lift.getCurrPosInches();
    }



    @Override
    public void open() {
        claw.setPosition(0.755);
        position = Claw.Position.OPEN;
    }

    @Override
    public void close() {
        claw.setPosition(0.765);
        position = Claw.Position.CLOSED;
    }

    public void alter(double amount) {
        claw.setPosition(claw.getPosition() + amount);
        position = Claw.Position.OTHER;
    }

    public void direct(double position) {
        claw.setPosition(position);
        this.position = Claw.Position.OTHER;
    }

    /**
     * Alters the power of the motor
     *
     * @param power Power to set the motor to
     */
    @Override
    public void move(double power) {
        lift.setPower(power);
    }

    /**
     * Gets the position of the motor
     *
     * @return the lift motor position
     */
    @Override
    public double getPosition() {
        return lift.getCurrentPosition();
    }

    /**
     * Gets the position of the claw servo
     * @return the claw servo position
     */
    public double getClawPosition() {
        return claw.getPosition();
    }

    /**
     * Gets the position the scissor lift is at in inches
     *
     * @return the position in inches
     */
    @Override
    public double getInches() {
        return lift.getCurrPosInches();
    }

    /**
     * Moves the scissor lift to the next position
     */
    @Override
    public void upLevel() {
        lift.runToPosition(lift.getCurrentPosition() + 20);
    }

    /**
     * Moves the scissor lift to the previous position
     */
    @Override
    public void downLevel() {
        lift.runToPosition(lift.getCurrentPosition() - 20);
    }
}
