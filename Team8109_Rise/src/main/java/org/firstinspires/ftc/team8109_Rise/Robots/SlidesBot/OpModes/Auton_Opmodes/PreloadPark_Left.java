package org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.OpModes.Auton_Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.team8109_Rise.Hardware.Intakes.ServoClaw;
import org.firstinspires.ftc.team8109_Rise.Math.Vectors.Vector3D;
import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms.Chassis;
import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms.Claw;
import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms.OdoRetract;
import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms.ServoIntakeArm;
import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms.ViperSlides;
import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms.Wrist;
import org.firstinspires.ftc.team8109_Rise.Sensors.Camera.OpenCV.VisionPipelines.ColorPipeline;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous
public class PreloadPark_Left extends LinearOpMode {
    OpenCvCamera camera; //TODO: Improve tracking
    ColorPipeline pipeline;

    ElapsedTime runtime = new ElapsedTime();
    ElapsedTime globalTime = new ElapsedTime();

    int cycleCounter = 1;

    public enum AutonState {
        PUSH_CONE,
        RETURN_TO_POLE,
        GO_TO_SCORE_PRELOAD,
        SCORE_PRELOAD,
        CYCLE,
        PARK
    }

    public enum CycleState{
        TO_CONE_STACK,
        PICK_UP_CONE,
        BOOST_UP,
        TO_HIGH_JUNCTION,
        SCORE_CONE
    }

    public enum ParkingStep{
        STEP_ONE,
        STEP_TWO,
        STEP_THREE
    }

    public enum ParkingZone{
        LEFT,
        MIDDLE,
        RIGHT
    }

    CycleAuton_PID.AutonState autonState;
    CycleAuton_PID.CycleState cycleState;
    CycleAuton_PID.ParkingStep parkingStep;
    CycleAuton_PID.ParkingZone parkingZone;

    Chassis chassis;
    ViperSlides slides;
    ServoIntakeArm arm;
    Wrist wrist;
    Claw claw;
    OdoRetract odoRetract;

    Vector3D targetPose = new Vector3D(0, 0, 0);
    double tolerance = 1;

    @Override
    public void runOpMode() throws InterruptedException {

        autonState = CycleAuton_PID.AutonState.PUSH_CONE;
        cycleState = CycleAuton_PID.CycleState.TO_CONE_STACK;
        parkingStep = CycleAuton_PID.ParkingStep.STEP_ONE;

        chassis = new Chassis(gamepad1, telemetry, hardwareMap);
        slides = new ViperSlides(gamepad1, telemetry, hardwareMap);
        arm = new ServoIntakeArm(gamepad1,telemetry, hardwareMap);
        wrist = new Wrist(gamepad1, hardwareMap);
        claw = new Claw(gamepad1, telemetry, hardwareMap);
        odoRetract = new OdoRetract(gamepad1, hardwareMap);

        claw.clawState = ServoClaw.ClawState.CLOSED;
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        pipeline = new ColorPipeline(telemetry);

        camera.setPipeline(pipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened()
            {
                camera.startStreaming(320,240, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });

        while (opModeInInit()){
            claw.setPosition();
            odoRetract.podState = OdoRetract.PodState.GROUND;

            if (pipeline.findColor() == ColorPipeline.Colors.BLUE){
                parkingZone = CycleAuton_PID.ParkingZone.LEFT;
            }

            if (pipeline.findColor() == ColorPipeline.Colors.RED){
                parkingZone = CycleAuton_PID.ParkingZone.RIGHT;
            }

            if (pipeline.findColor() == ColorPipeline.Colors.GREEN){
                parkingZone = CycleAuton_PID.ParkingZone.MIDDLE;
            }

            telemetry.addData("color", pipeline.findColor());
            telemetry.addData("color", pipeline.getHue());

            telemetry.update();
        }

        globalTime.reset();

        while (opModeIsActive()){
            chassis.update();
            chassis.updatePoseEstimate();

            autonLeftRed();

            chassis.goToPose(targetPose);

            slides.setSlidePower();
            claw.setPosition();
            arm.setArmPosition();
            wrist.setPosition();
            odoRetract.setPodPosition();

            Telemetry();
            telemetry.update();
        }
    }

    //TODO: odo retract can go with chassis control

    public void autonLeftRed(){
        switch (autonState){
            case PUSH_CONE:
                targetPose.set(60, 0, 0);
//

                claw.clawState = ServoClaw.ClawState.CLOSED;

                chassis.goToPose(targetPose);

                if (targetPose.findDistance(chassis.getPoseVector()) < tolerance){
                    autonState = CycleAuton_PID.AutonState.RETURN_TO_POLE;
                    runtime.reset();
                }
                break;
            case RETURN_TO_POLE:
                targetPose.set(46, 0, -0.773);
                chassis.goToPose(targetPose);

                if (targetPose.findDistance(chassis.getPoseVector()) < tolerance){
                    autonState = CycleAuton_PID.AutonState.GO_TO_SCORE_PRELOAD;
                    runtime.reset();
                }
                break;
            case GO_TO_SCORE_PRELOAD:
                slides.slidesState = ViperSlides.SlidesState.HIGH_JUNCTION;

                arm.servoPosition = ServoIntakeArm.ServoPosition.OUTTAKE_POSITION;
                wrist.wristPosition = Wrist.WristPosition.OUTTAKE_POSITION;
                targetPose.set(54.5, -3.5, -0.959);

                chassis.goToPose(targetPose);

                // could also vector sum all errors
                if (targetPose.findDistance(chassis.getPoseVector()) < tolerance){
                    autonState = CycleAuton_PID.AutonState.SCORE_PRELOAD;
                    runtime.reset();
                }
                break;
            case SCORE_PRELOAD:
                targetPose.set(54.5, -3.5, -0.959);

                if (runtime.seconds() > 0.5){
                    claw.clawState = ServoClaw.ClawState.OPEN;
                }

                // if ((claw.getPositionDegrees() > 175) && runtime.seconds() > 2){
                if ((claw.getPositionDegrees() > 140)){
                    autonState = CycleAuton_PID.AutonState.PARK;
                }
                break;
            case PARK:
                slides.slidesState = ViperSlides.SlidesState.GROUND;
                arm.servoPosition = ServoIntakeArm.ServoPosition.INTAKE_POSITION;
                wrist.wristPosition = Wrist.WristPosition.INTAKE_POSITION;
                claw.clawState = ServoClaw.ClawState.OPEN;

                switch (parkingStep){
                    case STEP_ONE:
                        targetPose.set(45, 0, 0);

                        if (targetPose.findDistance(chassis.getPoseVector()) < tolerance){
                            parkingStep = CycleAuton_PID.ParkingStep.STEP_TWO;
                            runtime.reset();
                        }
                        break;
                    case STEP_TWO:
                        targetPose.set(26, 0, 0);
                        if (targetPose.findDistance(chassis.getPoseVector()) < tolerance){
                            parkingStep = CycleAuton_PID.ParkingStep.STEP_THREE;
                            runtime.reset();
                        }
                        break;
                    case STEP_THREE:
                        switch (parkingZone){
                            case LEFT:
                                targetPose.set(26, 23, 0);
                                break;
                            case MIDDLE:
                                targetPose.set(26, 0, 0);

                                break;
                            case RIGHT:
                                targetPose.set(26, -23, 0);
                                break;
                        }
                }


//                switch (parkingZone){
//                    case LEFT:
//                        targetPose.set(45, 22, -Math.toRadians(90));
//                        break;
//                    case MIDDLE:
//                        targetPose.set(45, -3, -Math.toRadians(90));
//                        break;
//                    case RIGHT:
//                        targetPose.set(45, -27, -Math.toRadians(90));
//                        break;
//                }
        }
    }

    public void Telemetry(){
        //TODO: show the tolerance stuff and PID values and states
        telemetry.addData("Auton State", autonState);
        telemetry.addData("Cycle State", cycleState);
        telemetry.addData("cycle count", cycleCounter);

        telemetry.addData("runtime", runtime.seconds());
        telemetry.addData("claw position", claw.getPositionDegrees());

//        telemetry.addData("HeadingPID Proportion",chassis.HeadingPID.P);
        telemetry.addData("HeadingPID error",chassis.HeadingPID.error);
//        telemetry.addData("TranslationalX Proportion",chassis.TranslationalPID_X.P);
        telemetry.addData("TranslationalX error",chassis.TranslationalPID_X.error);
//        telemetry.addData("TranslationalY Proportion",chassis.TranslationalPID_Y.P);
        telemetry.addData("TranslationalY error",chassis.TranslationalPID_Y.error);
        telemetry.addData("Distance to Pose", targetPose.findDistance(chassis.getPoseVector()));
    }
}
