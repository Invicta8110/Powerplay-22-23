package org.firstinspires.ftc.team8109_Rise.Hardware.BotMechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.team8109_Rise.Hardware.Motor;

public class DoubleReverse4Bar {
    public Motor barMotor;
    public double kGravity;
    public double antiGravity;
    public double barLength;

    double kPos;

    public double motorPower = 0;

    public double maxExtension;
    public double BarLength;

    public DoubleReverse4Bar(String barMotorName, double GearRatio, HardwareMap hardwareMap){
        // TODO: move cpr to constructor
        barMotor = new Motor(barMotorName, 537.7, 1, 1, hardwareMap);
        barMotor.reset();
        barMotor.setBreakMode();
    }

    public DoubleReverse4Bar(String barMotorName, double GearRatio, double kGravity, HardwareMap hardwareMap){
        barMotor = new Motor(barMotorName, 537.7, 1, 1, hardwareMap);
        barMotor.reset();
        barMotor.setBreakMode();

        this.kGravity = kGravity;
    }

    public DoubleReverse4Bar(String barMotorName, double GearRatio, double kGravity, double kPos, HardwareMap hardwareMap){
        barMotor = new Motor(barMotorName, 537.7, 1, 1, hardwareMap);
        barMotor.reset();
        barMotor.setBreakMode();

        this.kGravity = kGravity;
        this.kPos = kPos;
    }

    public DoubleReverse4Bar(String barMotorName, double GearRatio, double kGravity, double kPos, double maxExtension, double barLength, HardwareMap hardwareMap){
        barMotor = new Motor(barMotorName, 537.7, 1, 1, hardwareMap);
        barMotor.reset();
        barMotor.setBreakMode();

        this.kGravity = kGravity;
        this.kPos = kPos;
        this.maxExtension = maxExtension;
        this.barLength = barLength;
    }

    public void setPower(double power){
        //TODO: actually do the math
//        antiGravity = kGravity * Math.cos((1/(maxExtension*Math.PI))*getHeight());
        antiGravity = kGravity*Math.sqrt((barLength*barLength) - ((getHeight()/2)*(getHeight()/2)));
        barMotor.setPower(antiGravity + power);
    }

    public double getHeight(){
        //TODO: Actually do the math
        return 3+barMotor.getCurrPosDegrees()*kPos;
    }
}
