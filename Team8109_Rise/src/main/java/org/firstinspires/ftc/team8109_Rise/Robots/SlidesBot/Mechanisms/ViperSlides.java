package org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.team8109_Rise.Control.PIDF_Controller;
import org.firstinspires.ftc.team8109_Rise.Hardware.Lifts.Slides;

public class ViperSlides extends Slides {

    Gamepad gamepad1;
    Telemetry telemetry;

    // Names for the motors in configuration
    static String[] name = {"slidesLeft", "slidesRight"};

    static double pulleyRadius = 0.752;

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

    boolean[] autonTestToggles = {true, false,  true, false,  true, false,  true, false,  true, false, true, false};

    boolean lastToggleX = false;
    boolean lastToggleUp = false;
    boolean lastToggleDown = false;

    public SlidesState slidesState;

    public PIDF_Controller slidesPID;

    public ViperSlides(Gamepad gamepad1, Telemetry telemetry, HardwareMap hardwareMap) {
        super(2, name, pulleyRadius, StringingMethod.CONTINUOUS, 2, 0.14, hardwareMap); //0.175

        // ki: 0.005
//        slidesPID = new PIDF_Controller(0.04, 0.03, 0, 0.01); //0.01
        slidesPID = new PIDF_Controller(0.13, 0.008, 0, 0.0175); //0.07, 0.0035, 0, 0.01

        //slidesPID = new PIDF_Controller(0.05, 0.0035, 0, 0.005); //0.0175, 0.01
        slidesPID.tolerance = 0.1;

        motors[0].reset();
        motors[1].reset();

        // One of the motors needs to be reversed since the motors face opposite directions
        motors[0].setDirectionReverse();

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
            //TODO: Add a state to the top High junction scoring state to manual control with dpad
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

                slidesPower = slidesPID.PIDF_Power(getHeight(), 0);
                break;

            case HIGH_JUNCTION:
                /* PID controller calculates the power needed to be set to the motors
                to stay at the target position (of 36 inches as my guess of what the high level is) */

//                slidesPower = slidesPID.PIDF_Power(getHeight(), 19.5); // 18.5

                slidesPower = slidesPID.PIDF_Power(getHeight(), 19); // 18.5

                if (gamepad1.dpad_up){
                    setPower(0.2);
                } else if (gamepad1.dpad_down){
                    setPower(-0.2);
                }

                break;

            case MIDDLE_JUNCTION:
                slidesPower = slidesPID.PIDF_Power(getHeight(), 10); // 18.5
                break;

            case LOW_JUNCTION:
                slidesPower = slidesPID.PIDF_Power(getHeight(), 6); // 18.5
                break;
            case CONESTACK_BOTTOM_MIDDLE:
                slidesPower = slidesPID.PIDF_Power(getHeight(), 2.3);
                break;

            case CONESTACK_MIDDLE:
                slidesPower = slidesPID.PIDF_Power(getHeight(), 3.4);
                break;

            case CONESTACK_TOP_MIDDLE:
                slidesPower = slidesPID.PIDF_Power(getHeight(), 4.4);
                break;

            case CONESTACK_TOP:
                slidesPower = slidesPID.PIDF_Power(getHeight(), 5.4);
                break;
        }
    }

    public void autonLevelsTesting(){
        setSlidePower();
        switch (slidesState){
            case GROUND:
                if ((gamepad1.dpad_up != lastToggleUp) && gamepad1.dpad_up && autonTestToggles[0]){
                    autonTestToggles[0] = false;
                    autonTestToggles[1] = true;

                    slidesState = SlidesState.CONESTACK_BOTTOM_MIDDLE;
                }
                break;
            case CONESTACK_BOTTOM_MIDDLE:
                if ((gamepad1.dpad_up != lastToggleUp) && gamepad1.dpad_up && autonTestToggles[2]){
                autonTestToggles[2] = false;
                autonTestToggles[3] = true;

                slidesState = SlidesState.CONESTACK_MIDDLE;
            }

                if ((gamepad1.dpad_down != lastToggleDown) && gamepad1.dpad_down && autonTestToggles[1]){
                    autonTestToggles[1] = false;
                    autonTestToggles[0] = true;

                    slidesState = SlidesState.GROUND;
                }
                break;
            case CONESTACK_MIDDLE:
                if ((gamepad1.dpad_up != lastToggleUp) && gamepad1.dpad_up && autonTestToggles[4]){
                    autonTestToggles[4] = false;
                    autonTestToggles[5] = true;

                    slidesState = SlidesState.CONESTACK_TOP_MIDDLE;
                }

                if ((gamepad1.dpad_down != lastToggleDown) && gamepad1.dpad_down && autonTestToggles[3]){
                    autonTestToggles[3] = false;
                    autonTestToggles[2] = true;

                    slidesState = SlidesState.CONESTACK_BOTTOM_MIDDLE;
                }
                break;
            case CONESTACK_TOP_MIDDLE:
                if ((gamepad1.dpad_up != lastToggleUp) && gamepad1.dpad_up && autonTestToggles[6]){
                    autonTestToggles[6] = false;
                    autonTestToggles[7] = true;

                    slidesState = SlidesState.CONESTACK_TOP;
                }

                if ((gamepad1.dpad_down != lastToggleDown) && gamepad1.dpad_down && autonTestToggles[5]){
                    autonTestToggles[5] = false;
                    autonTestToggles[4] = true;

                    slidesState = SlidesState.CONESTACK_MIDDLE;
                }
                break;
            case CONESTACK_TOP:
                if ((gamepad1.dpad_up != lastToggleUp) && gamepad1.dpad_up && autonTestToggles[8]){
                    autonTestToggles[8] = false;
                    autonTestToggles[9] = true;

                    slidesState = SlidesState.HIGH_JUNCTION;
                }

                if ((gamepad1.dpad_down != lastToggleDown) && gamepad1.dpad_down && autonTestToggles[7]){
                    autonTestToggles[7] = false;
                    autonTestToggles[6] = true;

                    slidesState = SlidesState.CONESTACK_TOP_MIDDLE;
                }
                break;

            case HIGH_JUNCTION:
                if ((gamepad1.dpad_down != lastToggleDown) && gamepad1.dpad_down && autonTestToggles[9]){
                    autonTestToggles[9] = false;
                    autonTestToggles[8] = true;

                    slidesState = SlidesState.CONESTACK_TOP;
                }
                break;
        }
        lastToggleUp = gamepad1.dpad_up;
        lastToggleDown = gamepad1.dpad_down;
    }
    public void toggleStates(){
        setSlidePower();
        switch (slidesState){
            case GROUND:
                if ((gamepad1.x != lastToggleX) && gamepad1.x && toggle1){
                    toggle1 = false;
                    toggle2 = true;

                    slidesState = SlidesState.HIGH_JUNCTION;
                }

//                if ((gamepad1.dpad_up != lastToggleUp) && gamepad1.dpad_up && autonTestToggles[0]){
//                    autonTestToggles[0] = false;
//                    autonTestToggles[1] = true;
//
//                    slidesState = SlidesState.LOW_JUNCTION;
//                }

                if (gamepad1.b){
                    slidesState = SlidesState.MANUAL;
                }

                break;

                // add manual control to this state (note: need to take off PID when if reaches setpoint within tolerance)
            case LOW_JUNCTION:
                if ((gamepad1.dpad_up != lastToggleUp) && gamepad1.dpad_up && autonTestToggles[2]){
                    autonTestToggles[2] = false;
                    autonTestToggles[3] = true;

                    slidesState = SlidesState.MIDDLE_JUNCTION;
                }

                if ((gamepad1.dpad_down != lastToggleDown) && gamepad1.dpad_down && autonTestToggles[1]){
                    autonTestToggles[1] = false;
                    autonTestToggles[0] = true;

                    slidesState = SlidesState.GROUND;
                }
                break;
            case MIDDLE_JUNCTION:
                if ((gamepad1.dpad_up != lastToggleUp) && gamepad1.dpad_up && autonTestToggles[4]){
                    autonTestToggles[4] = false;
                    autonTestToggles[5] = true;

                    slidesState = SlidesState.HIGH_JUNCTION;
                }

                if ((gamepad1.dpad_down != lastToggleDown) && gamepad1.dpad_down && autonTestToggles[3]){
                    autonTestToggles[3] = false;
                    autonTestToggles[2] = true;

                    slidesState = SlidesState.LOW_JUNCTION;
                }
                break;

            case HIGH_JUNCTION:
                if ((gamepad1.x != lastToggleX) && gamepad1.x && toggle2){
                    toggle2 = false;
                    toggle1 = true;

                    slidesState = SlidesState.GROUND;
                }

//                if ((gamepad1.dpad_down != lastToggleDown) && gamepad1.dpad_down && autonTestToggles[5]){
//                    autonTestToggles[5] = false;
//                    autonTestToggles[4] = true;
//
//                    slidesState = SlidesState.MIDDLE_JUNCTION;
//                }

                if (gamepad1.b){
                    slidesState = SlidesState.MANUAL;
                }

                break;
            case MANUAL:
                if ((gamepad1.x != lastToggleX) && gamepad1.x && toggle2){
                    toggle2 = false;
                    toggle1 = true;

                    slidesState = SlidesState.GROUND;
                }

                if ((gamepad1.x != lastToggleX) && gamepad1.x && toggle1){
                    toggle1 = false;
                    toggle2 = true;

                    slidesState = SlidesState.HIGH_JUNCTION;
                }
                break;
        }
        lastToggleX = gamepad1.x;
        lastToggleUp = gamepad1.dpad_up;
    }

    // Sends telemetry data for slides to a queue to be shown on driver station after telemetry.update() is called
    public void slidesTelemetry(){
        telemetry.addData("slides height", getHeight());
        telemetry.addData("Slide State", slidesState);
        telemetry.addData("Error", slidesPID.error);
        telemetry.addData("Anti-Gravity", kGravity);
        telemetry.addData("PID Power", slidesPower);
        telemetry.addData("Proportion", slidesPID.P);
        telemetry.addData("Integral", slidesPID.I);
        telemetry.addData("Derivative", slidesPID.D);
    }
}
