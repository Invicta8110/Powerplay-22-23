package org.firstinspires.ftc.team8110_Invicta.Hardware;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team8110_Invicta.Hardware.Drivetrains.StraferChassis;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.Claw;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.Motor;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.Lift;

public class StormyMcNuggets extends StraferChassis implements Lift, Claw {
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

    public void teleOpClaw(Gamepad gamepad) {
        Position state = this.getState();

        if (gamepad.a) {
            this.open();
        } else if (gamepad.y) {
            this.close();
        }
    }

    public void teleOpLift(Gamepad gamepad) {
        double down = gamepad.dpad_down ? 1 : 0;
        double up = gamepad.dpad_up ? 1 : 0;

        this.power(up-down);
    }

    public double getLiftPosition() {
        return lift.getCurrentPosition();
    }

    public double getLiftHeight() {
        return lift.getCurrPosInches();
    }



    @Override
    public void open() {
        claw.setPosition(0.705);
        position = Position.OPEN;
    }

    @Override
    public void close() {
        claw.setPosition(0.767);
        position = Position.CLOSED;
    }

    public void alter(double amount) {
        claw.setPosition(claw.getPosition() + amount);
        position = Position.OTHER;
    }

    public void direct(double position) {
        claw.setPosition(position);
        this.position = Position.OTHER;
    }

    /**
     * Alters the power of the motor
     *
     * @param power Power to set the motor to
     */
    @Override
    public void power(double power) {
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
    public void goToHigh() {
        lift.runToPosition(lift.getCurrentPosition() + 20);
    }

    /**
     * Moves the scissor lift to the previous position
     */
    @Override
    public void downLevel() {
        lift.runToPosition(lift.getCurrentPosition() - 20);
    }

    @Override
    public void reverse() {

    }
}
