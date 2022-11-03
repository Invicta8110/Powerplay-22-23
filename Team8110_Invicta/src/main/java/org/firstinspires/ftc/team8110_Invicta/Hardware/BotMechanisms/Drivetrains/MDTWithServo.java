package org.firstinspires.ftc.team8110_Invicta.Hardware.BotMechanisms.Drivetrains;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MDTWithServo extends MecanumDriveTrain {
    public CRServo servo;

    public MDTWithServo(HardwareMap hardwareMap) {
        super(hardwareMap);
        servo = "servo";
    }
}
