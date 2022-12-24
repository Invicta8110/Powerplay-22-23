package org.firstinspires.ftc.team7407_Vega.Control.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.team7407_Vega.Control.MotionControl.PIDF_Controller;
import org.firstinspires.ftc.team7407_Vega.Hardware.BotMechanisms.Drivetrains.MecanumDriveTrain;
import org.firstinspires.ftc.team7407_Vega.Hardware.BotMechanisms.Slides;

public class SlidesTest {
    Slides viperSlides;
    MecanumDriveTrain driveTrain;

    Gamepad gamepad1;
    Telemetry telemetry;

    double drive;
    double turn;
    double strafe;
    double fLeft;
    double fRight;
    double bLeft;
    double bRight;
    double max;


    boolean slidesToggle1 = true;
    boolean slidesToggle2 = false;
    boolean slidesToggle3 = true;
    boolean slidesToggle4 = false;

    boolean lastToggleDown = true;
    boolean lastToggleUp = true;

    public enum slideState{
        IDLE,
        HOME,
        FIVE_INCHES,
        TEN_INCHES,
    }

    double height = 10;

    PIDF_Controller slidesPID;
    slideState SlideState;

    public SlidesTest(Gamepad gamepad1, Telemetry telemetry, HardwareMap hardwareMap){
        viperSlides = new Slides("slides", 1, 0.44690708020204210283902560755002, height, hardwareMap);

        this.gamepad1 = gamepad1;
        this.telemetry = telemetry;

        slidesPID = new PIDF_Controller(0.5);
        SlideState = slideState.IDLE;
    }

    public void SlidesControl(){
        viperSlides.slidesMotor.setPower(Math.pow(gamepad1.right_trigger - gamepad1.left_trigger, 3));
//        switch (SlideState){
//            case IDLE:
//                viperSlides.slidesMotor.setPower(0);
//
//                if (gamepad1.x){
//                    SlideState = slideState.HOME;
//                }
//
//                break;
//            case HOME:
//                viperSlides.slidesMotor.setPower(slidesPID.PIDF_Power(viperSlides.getHeight(), 0, viperSlides.maxHeight));
//
//                if ((gamepad1.dpad_up != lastToggleUp) && gamepad1.dpad_up && slidesToggle1){
//                    slidesToggle1 = false;
//                    slidesToggle2 = true;
//
//                    SlideState = slideState.FIVE_INCHES;
//                }
//
//                lastToggleUp = gamepad1.dpad_up;
//
//                break;
//            case FIVE_INCHES:
//                viperSlides.slidesMotor.setPower(slidesPID.PIDF_Power(viperSlides.getHeight(), 5, viperSlides.maxHeight));
//
//                if ((gamepad1.dpad_up != lastToggleUp) && gamepad1.dpad_up && slidesToggle3){
//                    slidesToggle3 = false;
//                    slidesToggle4 = true;
//
//                    SlideState = slideState.FIVE_INCHES;
//                }
//
//                if ((gamepad1.dpad_down != lastToggleDown) && gamepad1.dpad_down && slidesToggle2){
//                    slidesToggle2 = false;
//                    slidesToggle1 = true;
//
//                    SlideState = slideState.HOME;
//                }
//
//                lastToggleUp = gamepad1.dpad_up;
//                lastToggleDown = gamepad1.dpad_down;
//
//                break;
//
//            case TEN_INCHES:
//                viperSlides.slidesMotor.setPower(slidesPID.PIDF_Power(viperSlides.getHeight(), 10, viperSlides.maxHeight));
//
//                if ((gamepad1.dpad_down != lastToggleDown) && gamepad1.dpad_down && slidesToggle4){
//                    slidesToggle4 = false;
//                    slidesToggle3 = true;
//
//                    SlideState = slideState.FIVE_INCHES;
//                }
//
//                lastToggleDown = gamepad1.dpad_down;
//
//                break;
//        }
    }

    public void Telemetry(){
        telemetry.addData("Slides Height", viperSlides.getHeight());
        telemetry.update();
    }
}
