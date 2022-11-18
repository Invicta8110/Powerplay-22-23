package org.firstinspires.ftc.team8109_Rise.Control.TeleOp;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.team8109_Rise.Control.MotionControl.PIDF_Controller;
import org.firstinspires.ftc.team8109_Rise.Hardware.BotMechanisms.DoubleReverse4Bar;

import javax.lang.model.element.Element;

public class DRv4B_Test_Control {
    public DoubleReverse4Bar DRv4B_Left;
    public DoubleReverse4Bar DRv4B_Right;

    public enum barState{
        HOME,
        HIGH_LEVEL
    }

    boolean barToggle1 = true;
    boolean barToggle2 = false;

    boolean lastToggleX = false;

    double drv4b_power = 0;
///
    public Telemetry telemetry;
    public Gamepad gamepad1;
    public PIDF_Controller Drv4B_PID;

    barState BarState;

    public DRv4B_Test_Control(String BarMotorLeftName, String BarMotorRightName, Telemetry telemetry, Gamepad gamepad1, HardwareMap hardwareMap){
        DRv4B_Left = new DoubleReverse4Bar(BarMotorLeftName, 0.714, 0.75, 7.5, 30, 13.3, -6, hardwareMap);
        DRv4B_Right = new DoubleReverse4Bar(BarMotorRightName, 0.714, 0.75, 7.5, 30, 13.3, -6, hardwareMap);

        DRv4B_Left.barMotor.reset();
        DRv4B_Right.barMotor.reset();

        DRv4B_Right.barMotor.setDirectionReverse();

        Drv4B_PID = new PIDF_Controller(0.5, 0, 0, 0); //5.25, 0.0001
        Drv4B_PID.tolerance = 2;

        this.gamepad1 = gamepad1;
        this.telemetry = telemetry;
        BarState = barState.HOME;
    }

    public void DoubleReverse4Bar(){
        switch (BarState){
            case HOME:
                if ((gamepad1.x != lastToggleX) && gamepad1.x && barToggle1){
                    barToggle1 = false;
                    barToggle2 = true;

                    BarState = barState.HIGH_LEVEL;
                }
                drv4b_power = Drv4B_PID.PIDF_Power(DRv4B_Left.getHeight(), 2, DRv4B_Left.maxExtension);
                break;
            case HIGH_LEVEL:
                drv4b_power = Drv4B_PID.PIDF_Power(DRv4B_Left.getHeight(), 34, DRv4B_Left.maxExtension);

                if ((gamepad1.x != lastToggleX) && gamepad1.x && barToggle1){
                    barToggle1 = false;
                    barToggle2 = true;

                    BarState = barState.HOME;
                }
                break;
        }

        lastToggleX = gamepad1.x;
        DRv4B_Left.setPower(drv4b_power);
        DRv4B_Right.setPower(drv4b_power);
    }

    public void Telemetry(){
        telemetry.addData("Left Bar Position", DRv4B_Left.getHeight());
        telemetry.addData("Right Bar Position", DRv4B_Right.getHeight());
        telemetry.addData("Angle", Math.toDegrees(DRv4B_Right.getAngle()));
        telemetry.addData("Anti Gravity", DRv4B_Left.antiGravity);
        telemetry.addData("PID Power", drv4b_power);
        telemetry.addData("Total Power", DRv4B_Left.antiGravity + drv4b_power);
        telemetry.update();
    }
}
