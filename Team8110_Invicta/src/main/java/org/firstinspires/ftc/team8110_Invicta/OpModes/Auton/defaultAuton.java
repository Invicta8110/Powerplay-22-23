package org.firstinspires.ftc.team8110_Invicta.OpModes.Auton;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team8110_Invicta.Hardware.ElefanteDrugMcNuggets;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.Motor;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.Webcam;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Pipelines.ColorDetector;
import org.firstinspires.ftc.team8110_Invicta.Hardware.States.Colors;
import org.openftc.easyopencv.OpenCvCamera;

@Autonomous
public class defaultAuton extends LinearOpMode {

    ElapsedTime current;
    ElefanteDrugMcNuggets robot;

    ColorDetector cat;

    Color_Control dog;

    OpenCvCamera camera;

    ElefanteDrugMcNuggets.ConeClaw sup;

    ElefanteDrugMcNuggets.ScissorLift yea;



    //DcMotor.RunMode
    @Override
    public void runOpMode() throws InterruptedException {

        camera = new Webcam(hardwareMap).getCamera();
        robot = new ElefanteDrugMcNuggets(hardwareMap);
        cat = new ColorDetector();
        camera.setPipeline(cat);

        robot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

//        robot.getLift().run(-2500);
//        telemetry.addData("Position Start", robot.getWheelPositions());
//        telemetry.update();
        robot.runForward(12);
//        telemetry.addData("Position End", robot.getWheelPositions());

//        cat.getColor();
//        robot.strafeLeft(12);
//
//        waitForStart();
//
//        ElapsedTime currentActionTimer = new ElapsedTime();
//        currentActionTimer.startTime();
//        gamepad1.dpad_down = true;
//        while (currentActionTimer.seconds() < 2) {
//            robot.teleOpLift(gamepad1);
//        }
//
//        cat.getColor();
//
//
//        if(cat.getColor() == Colors.RED){
//            robot.strafeLeft(16);
//            robot.runToPosition(24);
//        }
//        if(cat.getColor() == Colors.BLUE){
//            robot.strafeRight(16);
//            robot.runToPosition(24);
//        }
//        if(cat.getColor() == Colors.GREEN){
//            robot.runToPosition(24);
//        }
    }

    /*public void loopDirection(){
//       robot.getLift().run(5000);
        //scissorLift up
        //quick speed
        current = new ElapsedTime();
        current.startTime();
        gamepad1.dpad_down = true;
        while (current.seconds() < 4) {
            robot.teleOpLift(gamepad1);
        }

    }*/
}
