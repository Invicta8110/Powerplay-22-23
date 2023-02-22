package org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.OpModes.Auton_Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team8109_Rise.Hardware.Intakes.ServoClaw;
import org.firstinspires.ftc.team8109_Rise.Math.Vectors.Vector3D;
import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms.Chassis;
import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms.Claw;
import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms.OdoRetract;
import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms.ServoIntakeArm;
import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms.ViperSlides;
import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms.Wrist;
import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.OpModes.Auton_Opmodes.SavedCycleAutonPID.CycleAuton_PID_Real_Legit_Twenty;

@Autonomous
public class CycleAuton_Trapezoidal extends LinearOpMode {

    /*
    TODO:
    - Start Fast, go to tile 4 fast
    - Medium speed return to pole
    - Medium go to score preload
    - LineUp is medium high
    - Decelerate to cone stack (p controller)
    - Medium Move out
    - medium to high junction
    - heading error and translational error separation
     */


    ElapsedTime runtime = new ElapsedTime();
    ElapsedTime globalTime = new ElapsedTime();
    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
//    double fx = 578.272;
//    double fy = 578.272;
//    double cx = 402.145;
//    double cy = 221.506;
//
//    static final double FEET_PER_METER = 3.28084;
//
//
//    // UNITS ARE METERS
//    double tagsize = 0.166;
//
//    int ID_TAG_OF_INTEREST = 18; // Tag ID 18 from the 36h11 family
//
//    AprilTagDetection tagOfInterest = null;
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
        LINE_UP,
        WAIT_ONE,
        TO_CONE_STACK,
        PICK_UP_CONE,
        BOOST_UP,
        MOVE_OUT,
        WAIT_TWO,
        TO_HIGH_JUNCTION,
        WAIT_THREE,
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

    CycleAuton_PID_Real_Legit_Twenty.AutonState autonState;
    public CycleAuton_PID_Real_Legit_Twenty.CycleState cycleState;
    CycleAuton_PID_Real_Legit_Twenty.ParkingStep parkingStep;
    CycleAuton_PID_Real_Legit_Twenty.ParkingZone parkingZone;

    Chassis chassis;
    ViperSlides slides;
    ServoIntakeArm arm;
    Wrist wrist;
    Claw claw;
    OdoRetract odoRetract;

    boolean stop = false;
    Vector3D targetPose = new Vector3D(0, 0, 0);
    double translationalTolerance = 1;
    double headingTolerance = 0.02;

    @Override
    public void runOpMode() throws InterruptedException {

        autonState = CycleAuton_PID_Real_Legit_Twenty.AutonState.PUSH_CONE;
        cycleState = CycleAuton_PID_Real_Legit_Twenty.CycleState.TO_CONE_STACK;
        parkingStep = CycleAuton_PID_Real_Legit_Twenty.ParkingStep.STEP_ONE;

        chassis = new Chassis(gamepad1, telemetry, hardwareMap);
        slides = new ViperSlides(gamepad1, telemetry, hardwareMap);
        arm = new ServoIntakeArm(gamepad1,telemetry, hardwareMap);
        wrist = new Wrist(gamepad1, hardwareMap);
        claw = new Claw(gamepad1, telemetry, hardwareMap);
        odoRetract = new OdoRetract(gamepad1, hardwareMap);

        claw.clawState = ServoClaw.ClawState.CLOSED;
//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
//        pipeline = new ColorPipeline(telemetry);
//
//        camera.setPipeline(pipeline);
//        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
//            @Override
//            public void onOpened()
//            {
//                camera.startStreaming(320,240, OpenCvCameraRotation.UPRIGHT);
//            }
//
//            @Override
//            public void onError(int errorCode)
//            {
//
//            }
//        });

        parkingZone = CycleAuton_PID_Real_Legit_Twenty.ParkingZone.MIDDLE;
        odoRetract.podState = OdoRetract.PodState.GROUND;

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

//        ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();
//
        parkingZone = CycleAuton_PID_Real_Legit_Twenty.ParkingZone.LEFT;

        while (opModeInInit()){
            claw.setPosition();
            odoRetract.setPodPosition();
            slides.setSlidePower();
            arm.setArmPosition();
            wrist.setPosition();

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
//
//        while (opModeInInit()){
//            claw.setPosition();
//
//            odoRetract.setPodPosition();
//
//            slides.setSlidePower();
//            claw.setPosition();
//            arm.setArmPosition();
//            wrist.setPosition();

//            if (pipeline.findColor() == ColorPipeline.Colors.BLUE){
//                parkingZone = ParkingZone.LEFT;
//            }
//
//            if (pipeline.findColor() == ColorPipeline.Colors.RED){
//                parkingZone = ParkingZone.RIGHT;
//            }
//
//            if (pipeline.findColor() == ColorPipeline.Colors.GREEN){
//                parkingZone = ParkingZone.MIDDLE;
//            }
//
//            telemetry.addData("color", pipeline.findColor());
//            telemetry.addData("color", pipeline.getHue());

//            telemetry.update();
        }

        globalTime.reset();



        while (opModeIsActive()){
            chassis.update();
            chassis.updatePoseEstimate();

            autonLeftRed();

            slides.setSlidePower();
            claw.setPosition();
            arm.setArmPosition();
            wrist.setPosition();
            odoRetract.setPodPosition();

//            if (stop){
//                chassis.setPower(0);
//            } else {
//                chassis.goToPose(targetPose);
//            }

            chassis.goToPoseTrapezoidal(targetPose);

            Telemetry();
            telemetry.update();
        }
    }

    //TODO: odo retract can go with chassis control

    public void autonLeftRed(){
        switch (autonState){
            case PUSH_CONE:
                targetPose.set(60, 0, 0);

                claw.clawState = ServoClaw.ClawState.CLOSED;

                chassis.goToPosePID(targetPose);

                if (withinPoseTolerance()){
                    autonState = CycleAuton_PID_Real_Legit_Twenty.AutonState.RETURN_TO_POLE;

                    runtime.reset();
//                    if (runtime.seconds() > 0.5){
//                        autonState = AutonState.RETURN_TO_POLE;
//                        runtime.reset();
//                    }
                }
                break;
            case RETURN_TO_POLE:
                targetPose.set(48, 0.47, -0.394);
                chassis.goToPosePID(targetPose);

                if (withinPoseTolerance()){
                    autonState = CycleAuton_PID_Real_Legit_Twenty.AutonState.GO_TO_SCORE_PRELOAD;
                    runtime.reset();
                }
                break;
            case GO_TO_SCORE_PRELOAD:
                slides.slidesState = ViperSlides.SlidesState.HIGH_JUNCTION;

                arm.servoPosition = ServoIntakeArm.ServoPosition.OUTTAKE_POSITION;
                wrist.wristPosition = Wrist.WristPosition.OUTTAKE_POSITION;
                targetPose.set(55.7, -4.3, -0.959);

                chassis.goToPosePID(targetPose);

                // could also vector sum all errors
                if (withinPoseTolerance()){
                    autonState = CycleAuton_PID_Real_Legit_Twenty.AutonState.SCORE_PRELOAD;
                    runtime.reset();
                }
                break;
            case SCORE_PRELOAD:
                targetPose.set(54.275, -6.47, -0.6362);

                if (runtime.seconds() > 0.5){
                    claw.clawState = ServoClaw.ClawState.OPEN;
                }

                // if ((claw.getPositionDegrees() > 175) && runtime.seconds() > 2){
                if ((claw.getPositionDegrees() > 140)){
                    autonState = CycleAuton_PID_Real_Legit_Twenty.AutonState.CYCLE;
                }

                break;
            case CYCLE:
                switch (cycleState){
                    case LINE_UP:
                        targetPose.set(50, 12.724, -1.6166);

                        translationalTolerance = 0.25;
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
                        }

                        arm.servoPosition = ServoIntakeArm.ServoPosition.INTAKE_POSITION;
                        wrist.wristPosition = Wrist.WristPosition.INTAKE_POSITION;

                        if (withinPoseTolerance()){
                            cycleState = CycleAuton_PID_Real_Legit_Twenty.CycleState.WAIT_ONE;
                            runtime.reset();
                            translationalTolerance = 1;
                        }
                        break;
                    case WAIT_ONE:
                        if (runtime.seconds() > 0.2){
                            cycleState = CycleAuton_PID_Real_Legit_Twenty.CycleState.TO_CONE_STACK;
                            runtime.reset();
                        }
                        break;
                    case TO_CONE_STACK:
                        targetPose.set(50.3, 26.3, -1.6166);

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
                        }

                        arm.servoPosition = ServoIntakeArm.ServoPosition.INTAKE_POSITION;
                        wrist.wristPosition = Wrist.WristPosition.INTAKE_POSITION;

                        if (withinPoseTolerance()){
                            cycleState = CycleAuton_PID_Real_Legit_Twenty.CycleState.PICK_UP_CONE;
                            runtime.reset();
                        }
                        break;

                    case PICK_UP_CONE:

                        //TODO: have arm and slide also move a certain amount before going to next state in order to
                        targetPose.set(50.3, 26.3, -1.6166); //targetPose.set(50.3, 25.6444, -1.6166);
                        claw.clawState = ServoClaw.ClawState.CLOSED;

                        // TODO: Note that the claw won't actually reach this position closing
                        //runtime.seconds timer maybe needed
                        if ((claw.getPositionDegrees() < 110) && runtime.seconds() > 0.5) {
                            cycleState = CycleAuton_PID_Real_Legit_Twenty.CycleState.BOOST_UP;
                            runtime.reset();
                        }
                        break;

                    case BOOST_UP:
                        slides.slidesState = ViperSlides.SlidesState.HIGH_JUNCTION;
                        targetPose.set(50.3, 26.3, -1.6166);
                        claw.clawState = ServoClaw.ClawState.CLOSED;

                        if (runtime.seconds() > 0.75) {
                            cycleState = CycleAuton_PID_Real_Legit_Twenty.CycleState.TO_HIGH_JUNCTION;
                            runtime.reset();
                            translationalTolerance = 1;
                        }
                        break;
                    case MOVE_OUT:
//                        targetPose.set(50.3, 10, -1.609);
                        targetPose.set(48, 0.47, -0.394);
//                        targetPose.set(53, -2, -0.959);
                        slides.slidesState = ViperSlides.SlidesState.HIGH_JUNCTION;

                        arm.servoPosition = ServoIntakeArm.ServoPosition.OUTTAKE_POSITION;
                        wrist.wristPosition = Wrist.WristPosition.OUTTAKE_POSITION;

                        if (withinPoseTolerance()){
                            cycleState = CycleAuton_PID_Real_Legit_Twenty.CycleState.TO_HIGH_JUNCTION;
                            translationalTolerance = 1;
                            runtime.reset();
                        }
                        break;

                    case TO_HIGH_JUNCTION:
//                        targetPose.set(48.18, 13, -Math.toRadians(90));
//                        targetPose.set(54.775, -5.77, -0.58);
                        targetPose.set(56, -4.5, -0.959);
//                        targetPose.set(53, -2, -0.959);
                        slides.slidesState = ViperSlides.SlidesState.HIGH_JUNCTION;

                        arm.servoPosition = ServoIntakeArm.ServoPosition.OUTTAKE_POSITION;
                        wrist.wristPosition = Wrist.WristPosition.OUTTAKE_POSITION;

                        if (withinPoseTolerance()){
                            cycleState = CycleAuton_PID_Real_Legit_Twenty.CycleState.WAIT_THREE;
                            runtime.reset();
                        }
                        break;
                    case WAIT_THREE:
                        if (runtime.seconds() > 0.4){
                            cycleState = CycleAuton_PID_Real_Legit_Twenty.CycleState.SCORE_CONE;
                            runtime.reset();
                        }
                        break;
                    case SCORE_CONE:
//                        targetPose.set(54.775, -5.77,-0.58);
                        targetPose.set(56, -4.5, -0.959);
                        if (runtime.seconds() > 1){
                            claw.clawState = ServoClaw.ClawState.OPEN;
                        }

                        if ((claw.getPositionDegrees() > 140)/* && runtime.seconds() > 0.5*/){
                            cycleCounter++;

                            cycleState = CycleAuton_PID_Real_Legit_Twenty.CycleState.TO_CONE_STACK;
                        }
                        break;
                }
                if ((globalTime.seconds() > 35) || cycleCounter > 3){
                    autonState = CycleAuton_PID_Real_Legit_Twenty.AutonState.PARK;
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

                        if (withinPoseTolerance()){
                            parkingStep = CycleAuton_PID_Real_Legit_Twenty.ParkingStep.STEP_TWO;
                            runtime.reset();
                        }
                        break;
                    case STEP_TWO:
                        targetPose.set(26, 0, 0);
                        if (withinPoseTolerance()){
                            parkingStep = CycleAuton_PID_Real_Legit_Twenty.ParkingStep.STEP_THREE;
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

                        if (withinPoseTolerance()){
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

    public boolean withinPoseTolerance(){
        return (targetPose.getVector2D().findDistance(chassis.getPoseVector().getVector2D()) < translationalTolerance) && (chassis.HeadingPID.error > headingTolerance);
    }

//    void tagToTelemetry(AprilTagDetection detection) {
//        telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
//        telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x*FEET_PER_METER));
//        telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y*FEET_PER_METER));
//        telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z*FEET_PER_METER));
//        telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", Math.toDegrees(detection.pose.yaw)));
//        telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", Math.toDegrees(detection.pose.pitch)));
//        telemetry.addLine(String.format("Rotation Roll: %.2f degrees", Math.toDegrees(detection.pose.roll)));
//    }
}
