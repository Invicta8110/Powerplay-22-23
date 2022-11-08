package org.firstinspires.ftc.team7407_Vega.Hardware.BotMechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.team7407_Vega.Hardware.Motor;

public class Slides {
    public Motor slidesMotor;
    public double pulleyDiameter;
    public double maxHeight;

    public Slides(String Slides, double gearRatio, double pulleyDiameter, double maxHeight, HardwareMap hardwareMap){
        slidesMotor = new Motor(Slides, 537.7, pulleyDiameter, hardwareMap); //0.44690708020204210283902560755002
        this.pulleyDiameter = pulleyDiameter;
        this.maxHeight = maxHeight;
    }

    public double getHeight(){
        return Math.toRadians(slidesMotor.getCurrPosDegrees()) * pulleyDiameter;
    }
}
