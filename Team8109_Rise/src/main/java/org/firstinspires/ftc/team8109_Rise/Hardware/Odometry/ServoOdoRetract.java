package org.firstinspires.ftc.team8109_Rise.Hardware.Odometry;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public abstract class ServoOdoRetract {
    public Servo retractionServo;

    public ServoOdoRetract (String name, HardwareMap hardwareMap){
        retractionServo = hardwareMap.get(Servo.class, name);
    }
}
