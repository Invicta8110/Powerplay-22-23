package org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.team8109_Rise.Hardware.BotMechanisms.Arms.ServoArm;

public class ServoIntakeArm extends ServoArm {
    static String[] name = {"armLeft", "armRight"};

    Gamepad gamepad1;
    Telemetry telemetry;

    boolean toggle1 = true;
    boolean toggle2 = false;

    boolean lastToggleX = false;

    public enum ServoPosition {
        INTAKE_POSITION,
        OUTTAKE_POSITION
    }

    ServoPosition servoPosition;

    public ServoIntakeArm(Gamepad gamepad1, Telemetry telemetry, HardwareMap hardwareMap) {
        super(ServoArmType.DOUBLE_SERVO, name, hardwareMap);

        this.gamepad1 = gamepad1;
        this.telemetry = telemetry;

        servoPosition = ServoPosition.INTAKE_POSITION;
    }

    public void togglePosition(){
        switch (servoPosition){
            case INTAKE_POSITION:
                setAngle(0);

                if ((gamepad1.x != lastToggleX) && gamepad1.x && toggle1){
                    toggle1 = false;
                    toggle2 = true;

                    servoPosition = ServoPosition.OUTTAKE_POSITION;
                }
                break;

            case OUTTAKE_POSITION:
                setAngle(180);

                if ((gamepad1.x != lastToggleX) && gamepad1.x && toggle2){
                    toggle2 = false;
                    toggle1 = true;

                    servoPosition = ServoPosition.INTAKE_POSITION;
                }
                break;
        }
        lastToggleX = gamepad1.x;
    }

    public void setTelemetry(){
        telemetry.addData("Servo Angle", getPositionDegrees());
        telemetry.addData("Raw Position", armServo1.getPosition());
    }
}
