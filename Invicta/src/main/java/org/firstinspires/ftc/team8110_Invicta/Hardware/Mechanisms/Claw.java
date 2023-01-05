package org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
    private Servo claw;
    private Position position;

    public Claw(String name, HardwareMap hwmap) {
        claw = hwmap.get(Servo.class, name);
        this.close();
    }

    public Claw(HardwareMap hardwareMap) {
        this("claw", hardwareMap);
    }

    public void open() {
        claw.setPosition(0.98);
        position = Position.OPEN;
    }

    public void close() {
        claw.setPosition(1);
        position = Position.CLOSED;
    }

    public Position getState() {
        return position;
    }

    public enum Position {
        OPEN,
        CLOSED
    }

    public double getPosition() {
        return claw.getPosition();
    }
}
