package org.firstinspires.ftc.sampleCode.Hardware.BotMechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoImplEx;

public class Cage {
    public ServoImplEx cageSpin;

    public Cage (String name, HardwareMap hardwareMap){
        cageSpin = hardwareMap.get(ServoImplEx.class, name);
    }
}
