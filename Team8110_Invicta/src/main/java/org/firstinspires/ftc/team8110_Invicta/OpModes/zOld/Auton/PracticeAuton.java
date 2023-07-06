package org.firstinspires.ftc.team8110_Invicta.OpModes.zOld.Auton;

//import org.firstinspires.ftc.team8110_Invicta.Hardware.;


//@Autonomous
//public class PracticeAuton extends LinearOpMode {
//
//    Color_Control dark;
//    ColorDetector pig;
//    OpenCvCamera camera;
//     robot;
//
//    ElapsedTime runtime = new ElapsedTime();
//
//    public enum driveState {
//        trajectoryOne,
//        trajectoryTwo,
//        IDLE
//    }
//
//    Trajectory trajectoryOne;
//    Trajectory trajectoryTwo;
//
//    public driveState DriveState;
//
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//
//        //set up camera
//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Camera1"), cameraMonitorViewId);
//
//        dark = new Color_Control();
//        pig = new ColorDetector();
//        robot = new ElefanteDrugMcNuggets(HardwareMap hardwareMap);
//
//        Pose2d globalPose = new Pose2d(0,0,0);
//
//        robot.setPoseEstimate(globalPose);
//
//        if(pig.hsv_Value[0] ==  && pig.hsv_Value[1]==  && pig.hsv_Value[2] == ){
//            //end tangent is the position you want the robot facing
//            trajectoryOne = robot.trajectoryBuilder(globalPose).splineToConstantHeading(new Vector2d(18, 0), 0).build();
//            trajectoryTwo = robot.trajectoryBuilder(trajectoryOne.end()).splineTo(new Vector2d(18, -18), 0).build();
//            //red - forward, left
//            //18in
//            telemetry.addLine("RED");
//
//
//        }
//
//        if(pig.hsv_Value[0] ==  && pig.hsv_Value[1]==  && pig.hsv_Value[2] == ){
//            //green - forward
//            //18in
//
//            trajectoryOne = robot.trajectoryBuilder(globalPose).splineToConstantHeading(new Vector2d(18, 0), 0).build();
//            trajectoryTwo = robot.trajectoryBuilder(trajectoryOne.end()).splineTo(new Vector2d(18, 0), 0).build();
//            telemetry.addLine("GREEN");
//
//        }
//
//        if(pig.hsv_Value[0] ==  && pig.hsv_Value[1]==  && pig.hsv_Value[2] == ){
//            //blue - forward, right
//            //18in
//
//            trajectoryOne = robot.trajectoryBuilder(globalPose).splineToConstantHeading(new Vector2d(18, 0), 0).build();
//            trajectoryTwo = robot.trajectoryBuilder(trajectoryOne.end()).splineTo(new Vector2d(18, 18), 0).build();
//            telemetry.addLine("BLUE");
//
//        }
//
//        DriveState = driveState.trajectoryOne;
//
//        waitForStart();
//
//        runtime.reset();
//
//        while (opModeIsActive()){
//            switch (DriveState){
//                case trajectoryOne:
//                    if (!robot.isBusy()){
//                        DriveState = driveState.trajectoryTwo;
//                        robot.followTrajectoryAsync(trajectoryTwo);
//                    }
//                    break;
//                case trajectoryTwo:
//                    if (!robot.isBusy()){
//                        DriveState = driveState.IDLE;
//                    }
//                    break;
//                case IDLE:
//                    robot.setPower(0);
//
//            }
//
//            if (runtime.seconds() > 20){
//
//            }
//        }
//    }
//}
