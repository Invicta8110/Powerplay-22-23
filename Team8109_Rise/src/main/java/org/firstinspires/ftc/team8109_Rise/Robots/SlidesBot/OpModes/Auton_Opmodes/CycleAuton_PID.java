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
import org.firstinspires.ftc.team8109_Rise.Sensors.Camera.OpenCV.VisionPipelines.AprilTagDetectionPipeline;
import org.firstinspires.ftc.team8109_Rise.Sensors.Camera.OpenCV.VisionPipelines.ColorPipeline;
import org.firstinspires.ftc.team8109_Rise.Sensors.Camera.OpenCV.VisionPipelines.Signal_Identifier;
import org.firstinspires.ftc.team8109_Rise.UserInterface.AutonSelection;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

@Autonomous
public class CycleAuton_PID extends LinearOpMode {
    OpenCvCamera camera; //TODO: Improve tracking
    ColorPipeline pipeline;
//
    static final double FEET_PER_METER = 3.28084;

//    // Lens intrinsics
//    // UNITS ARE PIXELS
//    // NOTE: this calibration is for the C920 webcam at 800x448.
//    // You will need to do your own calibration for other configurations!
//    double fx = 578.272;
//    double fy = 578.272;
//    double cx = 402.145;
//    double cy = 221.506;
//
//    // UNITS ARE METERS
//    double tagsize = 0.166;
//
//    int ID_TAG_OF_INTEREST = 18; // Tag ID 18 from the 36h11 family
//
//    AprilTagDetection tagOfInterest = null;

    ElapsedTime runtime = new ElapsedTime();
    ElapsedTime globalTime = new ElapsedTime();

//    static Signal_Identifier pipeline;

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
        TO_HIGH_JUNCTION,
        SCORE_CONE
    }

    public enum ParkingStep{
        STEP_ONE,
        STEP_TWO
    }

    public enum ParkingZone{
        LEFT,
        MIDDLE,
        RIGHT
    }

    AutonState autonState;
    CycleState cycleState;
    ParkingStep parkingStep;
    ParkingZone parkingZone;

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

        autonState = AutonState.PUSH_CONE;
        cycleState = CycleState.TO_CONE_STACK;
        parkingStep = ParkingStep.STEP_ONE;

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
        // Set up webcam
//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
//        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);
//
//        camera.setPipeline(aprilTagDetectionPipeline);
//        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
//            @Override
//            public void onOpened()
//            {
//                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
//            }
//
//            @Override
//            public void onError(int errorCode)
//            {
//
//            }
//        });
//
//        telemetry.setMsTransmissionInterval(50);
//
//        ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();
//


//        autonSelection.setAutonMode();
//
//        while (opModeInInit()){
//            if (!pipeline.TargetRect.empty()){
//                telemetry.addData("Parking Zone", pipeline.signalPosition);
//            }
//
//            telemetry.addLine("Waiting for start");
//            telemetry.update();
//        }

//
//        pipeline.signalReadingToggle = false;
//
//        //TODO: remove when wanting to test auton with CV \/\/
//        pipeline.signalPosition = Signal_Identifier.SignalPosition.LEFT;

//        parkingZone = ParkingZone.LEFT;
//
//        while (opModeInInit()){
//            claw.setPosition();
//
//            if(currentDetections.size() != 0) {
//                boolean tagFound = false;
//
//                for(AprilTagDetection tag : currentDetections) {
//                    if(tag.id == 14) {
//                        tagOfInterest = tag;
//                        parkingZone = ParkingZone.LEFT;
//                        tagFound = true;
//                        break;
//                    }else if (tag.id == 4){
//                        tagOfInterest = tag;
//                        parkingZone = ParkingZone.MIDDLE;
//                        tagFound = true;
//                        break;
//                    } else if (tag.id == 8){
//                        tagOfInterest = tag;
//                        parkingZone = ParkingZone.RIGHT;
//                        tagFound = true;
//                        break;
//                    }
//                }
//
//                if(tagFound) {
//                    telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
//                    tagToTelemetry(tagOfInterest);
//                } else {
//                    telemetry.addLine("Don't see tag of interest :(");
//
//                    if(tagOfInterest == null) {
//                        telemetry.addLine("(The tag has never been seen)");
//                    } else {
//                        telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
//                        tagToTelemetry(tagOfInterest);
//                    }
//                }
//            } else {
//                telemetry.addLine("Don't see tag of interest :(");
//
//                if(tagOfInterest == null) {
//                    telemetry.addLine("(The tag has never been seen)");
//                } else {
//                    telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
//                    tagToTelemetry(tagOfInterest);
//                }
//            }
//
//            telemetry.update();
//            sleep(20);
//        }
//
//        while (opModeInInit()) {
//            claw.setPosition();
//            odoRetract.podState = OdoRetract.PodState.GROUND;
//        }

        while (opModeInInit()){
            claw.setPosition();
            odoRetract.podState = OdoRetract.PodState.GROUND;

            if (pipeline.findColor() == ColorPipeline.Colors.BLUE){
                parkingZone = ParkingZone.LEFT;
            }

            if (pipeline.findColor() == ColorPipeline.Colors.RED){
                parkingZone = ParkingZone.RIGHT;
            }

            if (pipeline.findColor() == ColorPipeline.Colors.GREEN){
                parkingZone = ParkingZone.MIDDLE;
            }

            telemetry.addData("color", pipeline.findColor());
            telemetry.addData("color", pipeline.getHue());

            telemetry.update();
        }
//        waitForStart();
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
                    autonState = AutonState.RETURN_TO_POLE;
                    runtime.reset();
                }
                break;
            case RETURN_TO_POLE:
                targetPose.set(46, 0, -0.773);
                chassis.goToPose(targetPose);

                if (targetPose.findDistance(chassis.getPoseVector()) < tolerance){
                    autonState = AutonState.GO_TO_SCORE_PRELOAD;
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
                    autonState = AutonState.SCORE_PRELOAD;
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
                    autonState = AutonState.CYCLE;
                }
                break;
            case CYCLE:
                switch (cycleState){
                    case TO_CONE_STACK:
                        targetPose.set(48.18, 27, -Math.toRadians(90));

                        switch (cycleCounter){
                            case 1:
                                slides.slidesState = ViperSlides.SlidesState.CONESTACK_TOP;
                                break;
                            case 2:
                                slides.slidesState = ViperSlides.SlidesState.CONESTACK_TOP_MIDDLE;
                                break;
                            case 3:
                                slides.slidesState = ViperSlides.SlidesState.CONESTACK_MIDDLE;
                                break;
                            case 4:
                                slides.slidesState = ViperSlides.SlidesState.CONESTACK_BOTTOM_MIDDLE;
                                break;
                            case 5:
                                slides.slidesState = ViperSlides.SlidesState.GROUND;
                                break;
                        }

                        arm.servoPosition = ServoIntakeArm.ServoPosition.INTAKE_POSITION;
                        wrist.wristPosition = Wrist.WristPosition.INTAKE_POSITION;
                        claw.clawState = ServoClaw.ClawState.OPEN;

                        if (targetPose.findDistance(chassis.getPoseVector()) < tolerance){
                            cycleState = CycleState.PICK_UP_CONE;
                            runtime.reset();
                        }
                        break;

                    case PICK_UP_CONE:

                        //TODO: have arm and slide also move a certain amount before going to next state in order to
                        targetPose.set(48.18, 27.5, -Math.toRadians(90));
                        claw.clawState = ServoClaw.ClawState.CLOSED;
//
//                        // TODO: Note that the claw won't actually reach this position closing
//                        //runtime.seconds timer maybe needed
                        if ((claw.getPositionDegrees() < 110) && runtime.seconds() > 0.5){
                            cycleState = CycleState.TO_HIGH_JUNCTION;
                        }
                        break;

                    case TO_HIGH_JUNCTION:
//                        targetPose.set(55.5, -10, -0.851);
//
//                        slides.slidesState = ViperSlides.SlidesState.HIGH_JUNCTION;
//
//                        arm.servoPosition = ServoIntakeArm.ServoPosition.OUTTAKE_POSITION;
//                        wrist.wristPosition = Wrist.WristPosition.OUTTAKE_POSITION;
//
//                        if (targetPose.findDistance(chassis.getPoseVector()) < tolerance){
//                            cycleState = CycleState.SCORE_CONE;
//                            runtime.reset();
//                        }
                        break;
                    case SCORE_CONE:
//                        targetPose.set(55.5, -12, -0.851);
//
//                        if (runtime.seconds() > 0.75){
//                            claw.clawState = ServoClaw.ClawState.OPEN;
//                        }
//
//                        if ((claw.getPositionDegrees() > 140)/* && runtime.seconds() > 0.5*/){
//                            cycleCounter++;
//
//                            cycleState = CycleState.TO_CONE_STACK;
//                        }
                        break;
                }
                if ((globalTime.seconds() > 27) || cycleCounter > 5){
                    autonState = AutonState.PARK;
                }
                break;
            case PARK:
//                odoRetract.podState = OdoRetract.PodState.RETRACTED;
//                slides.slidesState = ViperSlides.SlidesState.GROUND;
//                arm.servoPosition = ServoIntakeArm.ServoPosition.INTAKE_POSITION;
//                wrist.wristPosition = Wrist.WristPosition.INTAKE_POSITION;
//                claw.clawState = ServoClaw.ClawState.OPEN;
//
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

    public void autonRedRight(){

    }

    public void autonBlueLeft(){

    }

    public void autonBlueRight(){

    }

    public void AprilTagsChecker(){

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
