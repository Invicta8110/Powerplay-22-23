package org.firstinspires.ftc.sampleCode.Hardware.BotMechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.sampleCode.Hardware.Motor;

public class OpTake {

    public Motor intakeMotor;

    double intakeAngle = 0;
    int circleCondition = 0;

    public OpTake(String name, HardwareMap hardwareMap){
        intakeMotor = new Motor(name, 537.7, 0, 3.84, hardwareMap); // "intake"

        intakeMotor.reset();
    }

    public double IntakeAngle(){
        if (intakeAngle > 360){
            circleCondition -= 360;
        }

        if (intakeAngle < -360){
            circleCondition += 360;
        }

        intakeAngle = intakeMotor.getCurrPosDegrees() + circleCondition;

        return intakeAngle;
    }
}
