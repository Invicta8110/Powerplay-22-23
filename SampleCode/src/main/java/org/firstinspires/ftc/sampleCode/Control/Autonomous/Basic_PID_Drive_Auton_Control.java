package org.firstinspires.ftc.sampleCode.Control.Autonomous;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.sampleCode.Hardware.BotMechanisms.Drivetrains.MecanumDriveTrain;
import org.firstinspires.ftc.sampleCode.Control.MotionControl.PIDF_Controller;

public class Basic_PID_Drive_Auton_Control {

    // Create a mecanum chassis object
    public MecanumDriveTrain chassis;

    // Create object to interact with telemetry
    public Telemetry telemetry;

    public enum driveState{
        STEP_ONE,
        STEP_TWO,
        IDLE
    }

    public int driveStep = 1;

    public double power;

    public PIDF_Controller PID;

    driveState DriveState;

    public Basic_PID_Drive_Auton_Control(String flName, String frName, String brName, String blName, HardwareMap hardwareMap, Telemetry telemetry){

        this.PID = new PIDF_Controller(0.6, 0.0001);
        this.chassis = new MecanumDriveTrain(flName, frName, brName, blName, hardwareMap);

        PID.tolerance = 0.05;

        this.chassis.reset();

        this.telemetry = telemetry;

        DriveState = driveState.STEP_ONE;
    }


    public void Drive(){

        // TODO: Actually test this code because this was written off the top of my head
        switch (DriveState){
            case STEP_ONE:
                power = PID.PIDF_Power(-chassis.frontLeft.getCurrPosInches(), 15, 15); // 0.675, 0.0032, 0, 0.01

                chassis.setPower(-power);

                if (PID.getError() < PID.tolerance){
                    chassis.reset();

                    // Make a nested method thingy so that I can just put one for all
                    chassis.setPower(0);
                    DriveState = driveState.STEP_TWO;
                }
                break;
            case STEP_TWO:
                power = PID.PIDF_Power(-chassis.frontLeft.getCurrPosInches(), -15, -15);
                chassis.setPower(power);

                if (Math.abs(PID.getError()) < PID.tolerance){
                    chassis.reset();

                    // Make a nested method thingy so that I can just put one for all
                    chassis.setPower(0);
                    DriveState = driveState.IDLE;
                }
                break;
            case IDLE:
                chassis.setPower(0);
        }
    }

    public void Telemetry(){
        telemetry.addData("Auton Step", DriveState);
        telemetry.addData("PID Error", PID.error);
        telemetry.addData("Motor Power", power);
        telemetry.update();
    }
}
