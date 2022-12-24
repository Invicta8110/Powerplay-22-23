package org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.team8109_Rise.Control.PIDF_Controller;
import org.firstinspires.ftc.team8109_Rise.Hardware.BotMechanisms.Slides;
import org.firstinspires.ftc.team8109_Rise.Hardware.Motor;

public class ViperSlides extends Slides {

    Gamepad gamepad1;
    Telemetry telemetry;

    static String[] name = {"slidesLeft", "slidesRight"};

    static double pulleyDiameter = 0.702;

    double slidesPower = 0;

    public enum SlidesState{
        HIGH,
        GROUND,
        MANUAL
    }

    SlidesState slidesState;

    PIDF_Controller slidesPID;
    public ViperSlides(Gamepad gamepad1, HardwareMap hardwareMap) {
        super(2, name, pulleyDiameter, StringingMethod.CASCADE, 2, 0, hardwareMap);
        slidesPID = new PIDF_Controller(0);
        slidesState = SlidesState.MANUAL;

        this.gamepad1 = gamepad1;
    }

    public void setSlidePosition(){
        setPower(slidesPower);
        switch (slidesState){
            case MANUAL:
                if (gamepad1.dpad_up){
                    slidesPower = 0.2;
                } else if (gamepad1.dpad_down){
                    slidesPower = -0.2;
                } else {
                    slidesPower = 0;
                }
                break;
            case GROUND:

                break;

            case HIGH:

                break;
        }
    }
}
