package org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class ScissorLift {
    Motor motor;
    int level;

    /**
     * Constructor with name I'm probably going with
     * @param hwmap HardwareMap object
     */
    public ScissorLift(HardwareMap hwmap) {
        motor = new Motor("lift", hwmap);
    }

    /**
     * Constructor in case I change the name
     * @param name Name of the motor in control hub config
     * @param hwmap HardwareMap object
     */
    public ScissorLift(String name, HardwareMap hwmap) {
        motor = new Motor(name, hwmap);
    }

    /**
     * Moves the scissor lift
     * @param power Power to move the lift at
     */
    public void move(double power) {
        motor.setPower(power);
    }

    /** Gets the location of the scissor lift's motor
     * @return the location of the scissor lift's motor
     */
    public int getPosition() {
        return motor.getCurrentPosition();
    }

    public int getInches() {
        return (int) motor.getCurrPosInches();
    }

    /** Gets the level the scissorlift is at
     * @return the level the scissorlift is at
     */
    public int getLevel() {
        return level;
    }

    /**
     * Moves the scissor lift to the next position
     */
    public void upLevel() {
        motor.runToPosition(0, 0.5); //TODO: figure out the positions
    }

    /**
     * Moves the scissor lift to the previous position
     */
    public void downLevel() {
        motor.runToPosition(0, 0.5); //TODO: figure out the positions
    }
}

