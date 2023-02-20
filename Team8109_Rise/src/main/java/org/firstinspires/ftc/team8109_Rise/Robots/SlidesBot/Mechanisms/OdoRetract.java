package org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.team8109_Rise.Hardware.Odometry.ServoOdoRetract;

public class OdoRetract extends ServoOdoRetract {
    Gamepad gamepad1;

    public enum PodState{
        GROUND,
        RETRACTED
    }

    boolean toggle1 = true;
    boolean toggle2 = false;

    boolean lastToggleBumperRight = false;

    public PodState podState;
    public OdoRetract(Gamepad gamepad1, HardwareMap hardwareMap) {
        super("odoRetract", hardwareMap);

        podState = PodState.GROUND;

        this.gamepad1 = gamepad1;
    }

    public void setPodPosition(){
        switch (podState) {
            case GROUND:
                retractionServo.setPosition(0.7);
                break;
            case RETRACTED:
                retractionServo.setPosition(0.95);
                break;
        }
    }

    public void toggleState(){
        setPodPosition();
        switch (podState){
            case GROUND:
                if ((gamepad1.right_bumper != lastToggleBumperRight) && gamepad1.right_bumper && toggle1){
                    toggle1 = false;
                    toggle2 = true;

                    podState = PodState.RETRACTED;
                }
                break;
            case RETRACTED:
                if ((gamepad1.right_bumper != lastToggleBumperRight) && gamepad1.right_bumper && toggle2){
                    toggle2 = false;
                    toggle1 = true;

                    podState = PodState.GROUND;
                }
                break;
        }
        lastToggleBumperRight = gamepad1.right_bumper;
    }
}