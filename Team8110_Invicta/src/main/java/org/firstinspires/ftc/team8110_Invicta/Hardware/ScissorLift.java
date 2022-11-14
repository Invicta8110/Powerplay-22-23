package org.firstinspires.ftc.team8110_Invicta.Hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ScissorLift {
    Servo servo;

    public ScissorLift(String name, HardwareMap hwmap) {
        servo = hwmap.get(Servo.class, name);
    }


    public void alterPosition(double input) {
        servo.setPosition(input);
    }

    public double getPosition() {
        return servo.getPosition();
    }


    public void ground() {
        servo.getPosition();
    }
}

