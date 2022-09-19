package org.firstinspires.ftc.sampleCode.Control.TeleOp;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.sampleCode.Hardware.BotMechanisms.Drivetrains.H_Drive;
import org.firstinspires.ftc.sampleCode.Hardware.BotMechanisms.Drivetrains.H_Drive;

public class H_Drive_Test_TeleOp_Control {
    public H_Drive drivetrain;
    public Telemetry telemetry;
    public Gamepad gamepad1;

    public double drive;
    public double turn;
    public  double strafe;

    public double left;
    public double right;
    public double middle;

    public double max;

    public H_Drive_Test_TeleOp_Control(String leftName, String rightName, String middleName, double cpr, double wheelDiameter, double GearRatio, HardwareMap hardwareMap, Telemetry telemetry, Gamepad gamepad1){
        drivetrain = new H_Drive(leftName, rightName, middleName, cpr, wheelDiameter, GearRatio, hardwareMap);

        drivetrain.reset();
        drivetrain.setBreakMode();

        this.telemetry = telemetry;
        this.gamepad1 = gamepad1;
    }

    public void Drive(){
        drive = Math.pow(gamepad1.left_stick_y, 3); //Between -1 and 1
        turn = Math.pow(gamepad1.right_stick_x, 3);
        strafe = Math.pow(gamepad1.left_stick_x, 3);

        left = drive + turn;
        right = drive - turn;
        middle = strafe;

        max = Math.max(Math.max(Math.abs(left), Math.abs(right)), Math.abs(middle));
        if (max > 1.0) {
            left /= max;
            right /= max;
            middle /= max;
        }

        drivetrain.setPower(left, right, middle);
    }
}
