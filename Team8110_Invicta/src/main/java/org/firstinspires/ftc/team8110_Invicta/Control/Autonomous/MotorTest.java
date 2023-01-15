package org.firstinspires.ftc.team8110_Invicta.Control.Autonomous;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.Motor;

public class MotorTest {
    Motor testMotor;

    public MotorTest(String name, HardwareMap hardwareMap){
        testMotor = new Motor(name, 537.7, 0.18, hardwareMap);

    }

    public void MotorRun(){
        testMotor.setPower(0.5);
    }
}
