package org.firstinspires.ftc.team8110_Invicta.Hardware.Drivetrains;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.Motor;

public class H_Drive {
    Motor left;
    Motor right;
    Motor middle;

    public H_Drive(String leftName, String rightName, String middleName, HardwareMap hardwareMap){
        left = new Motor(leftName, hardwareMap);
        right = new Motor(rightName, hardwareMap);
        middle = new Motor(middleName, hardwareMap);
    }

    public H_Drive(String leftName, String rightName, String middleName, double cpr, double wheelDiameter, double GearRatio, HardwareMap hardwareMap){
        left = new Motor(leftName, cpr, wheelDiameter, GearRatio, hardwareMap);
        right = new Motor(rightName, cpr, wheelDiameter, GearRatio, hardwareMap);
        middle = new Motor(middleName, cpr, wheelDiameter, GearRatio, hardwareMap);

        right.setDirectionReverse();
    }

    public void setPower(double leftPower, double rightPower, double middlePower){
        left.setPower(leftPower);
        right.setPower(rightPower);
        middle.setPower(middlePower);
    }

    public void reset(){
        left.reset();
        right.reset();
        middle.reset();
    }

    public void setBreakMode(){
        left.setBreakMode();
        right.setBreakMode();
        middle.setBreakMode();
    }
}
