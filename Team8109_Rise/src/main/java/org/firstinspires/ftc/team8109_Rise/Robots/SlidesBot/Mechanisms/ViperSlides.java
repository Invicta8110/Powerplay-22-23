package org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms;

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
        GROUND,
        LOW_JUNCTION,
        MIDDLE_JUNCTION,
        HIGH_JUNCTION,
        CONESTACK_BOTTOM_MIDDLE,
        CONESTACK_MIDDLE,
        CONESTACK_TOP_MIDDLE,
        CONESTACK_TOP,
        MANUAL
    }

    boolean toggle1 = true;
    boolean toggle2 = false;

    boolean lastToggleX = false;

    public SlidesState slidesState;

    PIDF_Controller slidesPID;

    public ViperSlides(Gamepad gamepad1, Telemetry telemetry, HardwareMap hardwareMap) {
        super(2, name, pulleyDiameter, StringingMethod.CASCADE, 2, 0.1, hardwareMap);

        // ki: 0.005
        slidesPID = new PIDF_Controller(0.03, 0.03, 0, 0.02);

        motors[0].reset();
        motors[1].reset();

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
                    slidesPower = 0.5;
                } else if (gamepad1.dpad_down){
                    slidesPower = -0.5;
                } else {
                    slidesPower = 0;
                }
                break;

            case GROUND:
                /* PID controller calculates the power needed to be set to the motors
                to stay at the target position (of 2 inches as my guess of what ground level is) */

                slidesPower = slidesPID.PIDF_Power(getHeight(), 1);
                break;

            case HIGH_JUNCTION:
                /* PID controller calculates the power needed to be set to the motors
                to stay at the target position (of 36 inches as my guess of what the high level is) */
                slidesPower = slidesPID.PIDF_Power(getHeight(), 20);
                break;

            case CONESTACK_BOTTOM_MIDDLE:
                slidesPower = slidesPID.PIDF_Power(getHeight(), 2);
                break;

            case CONESTACK_MIDDLE:
                slidesPower = slidesPID.PIDF_Power(getHeight(), 3);
                break;

            case CONESTACK_TOP_MIDDLE:
                slidesPower = slidesPID.PIDF_Power(getHeight(), 4);
                break;

            case CONESTACK_TOP:
                slidesPower = slidesPID.PIDF_Power(getHeight(), 5);
                break;
        }
    }

    //TODO: Cycle count

    public void toggleStates(){
        switch (slidesState){
            case GROUND:
                if ((gamepad1.x != lastToggleX) && gamepad1.x && toggle1){
                    toggle1 = false;
                    toggle2 = true;

                    slidesState = SlidesState.HIGH_JUNCTION;
                }

                break;
            case HIGH_JUNCTION:
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
        telemetry.addData("Target Position", slidesState);
        telemetry.addData("PID Power", slidesPower);
        telemetry.addData("Proportion", slidesPID.P);
        telemetry.addData("Integral", slidesPID.I);
        telemetry.addData("Derivative", slidesPID.D);
    }
}
