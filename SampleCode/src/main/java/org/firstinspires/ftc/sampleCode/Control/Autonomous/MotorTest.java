package org.firstinspires.ftc.sampleCode.Control.Autonomous;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.sampleCode.Hardware.Motor;

public class MotorTest {
    Motor testMotor;

    public MotorTest(String name, HardwareMap hardwareMap){
        testMotor = new Motor(name, 537.7, 0.18, hardwareMap);

    }

    public void MotorRun(){
        testMotor.setPower(0.5);
    }
}
