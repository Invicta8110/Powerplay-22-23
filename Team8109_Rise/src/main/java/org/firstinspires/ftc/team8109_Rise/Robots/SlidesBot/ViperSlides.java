package org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.team8109_Rise.Control.PIDF_Controller;
import org.firstinspires.ftc.team8109_Rise.Hardware.BotMechanisms.Lifts.Slides;

public class ViperSlides extends Slides {

    Gamepad gamepad1;
    Telemetry telemetry;
    // Names for the motors in configuration
    static String[] name = {"slidesLeft", "slidesRight"};

    static double pulleyDiameter = 0.702;

    double slidesPower = 0;

    public enum SlidesState{
        HIGH,
        GROUND,
        MANUAL
    }

    boolean toggle1 = true;
    boolean toggle2 = false;

    boolean lastToggleX = false;

    SlidesState slidesState;

    PIDF_Controller slidesPID;

    public ViperSlides(Gamepad gamepad1, Telemetry telemetry, HardwareMap hardwareMap) {
        super(2, name, pulleyDiameter, StringingMethod.CASCADE, 2, 0, hardwareMap);

        // ki: 0.008
        slidesPID = new PIDF_Controller(0.01, 0, 0, 0);

        // One of the motors needs to be reversed since the motors face opposite directions
        motors[1].setDirectionReverse();

        // Need to use the instances of gamepad1 and telemetry from the class LinearOpmode because that's what the code runs
        this.gamepad1 = gamepad1;
        this.telemetry = telemetry;

        // The starting state of the slides is to be in manual control
        slidesState = SlidesState.GROUND;
    }

    // Method looped to continually set power to slides based on state
    public void setSlidePower(){
        // Continually sets the power to the slides motors
        setPower(slidesPower);

        switch (slidesState){
            case MANUAL:
                // Changes power set to slides when using d-pad
                if (gamepad1.dpad_up){
                    slidesPower = 0.2;
                } else if (gamepad1.dpad_down){
                    slidesPower = -0.2;
                } else {
                    slidesPower = 0;
                }
                break;

            case GROUND:
                /* PID controller calculates the power needed to be set to the motors
                to stay at the target position (of 2 inches as my guess of what ground level is) */

                slidesPower = slidesPID.PIDF_Power(getHeight(), 2);
                break;

            case HIGH:
                /* PID controller calculates the power needed to be set to the motors
                to stay at the target position (of 36 inches as my guess of what the high level is) */
                slidesPower = slidesPID.PIDF_Power(getHeight(), 36);
                break;
        }
    }

    public void toggleStates(){
        switch (slidesState){
            case GROUND:
                if ((gamepad1.x != lastToggleX) && gamepad1.x && toggle1){
                    toggle1 = false;
                    toggle2 = true;

                    slidesState = SlidesState.HIGH;
                }

                break;
            case HIGH:
                if ((gamepad1.x != lastToggleX) && gamepad1.x && toggle2){
                    toggle2 = false;
                    toggle1 = true;

                    slidesState = SlidesState.GROUND;
                }

                break;
        }
        lastToggleX = gamepad1.x;
    }

    // Sends telemetry data for slides to a queue to be shown on driver station after telemetry.update() is called
    public void slidesTelemetry(){
        telemetry.addData("slides height", getHeight());
        telemetry.addData("PID Power", slidesPower);
        telemetry.addData("Proportion", slidesPID.P);
        telemetry.addData("Proportion", slidesPID.I);
    }
}
