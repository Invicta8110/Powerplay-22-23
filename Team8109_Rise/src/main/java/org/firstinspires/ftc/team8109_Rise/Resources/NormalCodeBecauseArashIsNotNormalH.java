package org.firstinspires.ftc.team8109_Rise.Resources;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class NormalCodeBecauseArashIsNotNormalH {
        public CRServo vacLeft;
        public CRServo vacRight;

        public CRServo rotateRL;
        public CRServo rotateUD;

        HardwareMap hardwareMap;

        public void init(HardwareMap hardwareMap) {
            vacLeft = hardwareMap.get(CRServo.class, "vacLeft");
            vacRight = hardwareMap.get(CRServo.class, "vacRight");

            rotateRL = hardwareMap.get(CRServo.class, "rotateRL");
            rotateUD = hardwareMap.get(CRServo.class, "rotateUD");

            vacLeft.setPower(0);
            vacRight.setPower(0);

            rotateRL.setPower(0);
            rotateUD.setPower(0);
        }
}
