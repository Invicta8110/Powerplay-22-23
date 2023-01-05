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
        motor.setDirectionReverse();
    }


    /**
     * Constructor in case I change the name
     * @param name Name of the motor in control hub config
     * @param hwmap HardwareMap object
     */
    public ScissorLift(String name, HardwareMap hwmap) {
        motor = new Motor(name, hwmap);
        motor.setDirectionReverse();
    }

    /**
     * Alters the power of the motor
     * @param power Power to set the motor to
     */
    public void move(double power) {
        motor.setPower(power);
    }

    /**
     * Gets the position of the motor
     * @return the motor position
     */
    public int getPosition() {
        return motor.getCurrentPosition();
    }

    /**
     * Gets the position the scissor lift is at in inches
     * @return the position in inches
     */
    public double getInches() {
        return motor.getCurrPosInches();
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

