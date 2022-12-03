package org.firstinspires.ftc.team8109_Rise.Hardware.BotMechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.team8109_Rise.Hardware.Motor;

public class DoubleReverse4Bar {
    public Motor barMotor;

    public DoubleReverse4Bar(String barMotorName, double GearRatio, HardwareMap hardwareMap){
        barMotor = new Motor(barMotorName, 537.7, 1, 1, hardwareMap);
        barMotor.reset();
        barMotor.setBreakMode();
    }


}
