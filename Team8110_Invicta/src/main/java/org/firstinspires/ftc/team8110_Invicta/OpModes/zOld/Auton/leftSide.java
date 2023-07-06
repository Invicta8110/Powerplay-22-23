package org.firstinspires.ftc.team8110_Invicta.OpModes.zOld.Auton;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team8110_Invicta.Hardware.ElefanteMcNuggets;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.Webcam;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Pipelines.ColorDetector;
import org.openftc.easyopencv.OpenCvCamera;

public class leftSide extends LinearOpMode {

    ElapsedTime current;

    ElefanteMcNuggets robot;

    ColorDetector cat;

    Color_Control dog;

    OpenCvCamera camera;

    ElefanteMcNuggets.ConeClaw sup;

    ElefanteMcNuggets.ScissorLift yea;

    public int currentPos;


    @Override
    public void runOpMode() throws InterruptedException {

        camera = new Webcam(hardwareMap).getCamera();
        robot = new ElefanteMcNuggets(hardwareMap);
        cat = new ColorDetector();
        camera.setPipeline(cat);


    }

    public void beginningPart(){
        ElapsedTime currentActionTimer = new ElapsedTime();
        currentActionTimer.startTime();
        gamepad1.dpad_down = true;
        while (currentActionTimer.seconds() < 2) {
            robot.teleOpLift(gamepad1);
        }
        sup.open();
        robot.runToPosition(2);
        gamepad1.dpad_up = true;
        while (currentActionTimer.seconds() < 2) {
            robot.teleOpLift(gamepad1);
        }
        sup.close();

    }
}
