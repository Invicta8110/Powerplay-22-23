package org.firstinspires.ftc.sampleCode.Hardware.BotMechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.sampleCode.Hardware.Motor;

public class Chain_Mechanism {
    public Motor chainMotor;

    public Chain_Mechanism(String name, HardwareMap hardwareMap){
        chainMotor = new Motor(name, 537.7, 0, hardwareMap);
        chainMotor.reset();
    }
}
