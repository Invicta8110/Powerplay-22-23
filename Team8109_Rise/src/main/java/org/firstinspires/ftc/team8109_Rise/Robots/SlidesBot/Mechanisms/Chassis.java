package org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Mechanisms;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.team8109_Rise.Control.PIDF_Controller;
import org.firstinspires.ftc.team8109_Rise.Hardware.Drivetrains.MecanumDriveTrain;
import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Sensors.Odometry.OdometryLocalizer;
import org.firstinspires.ftc.team8109_Rise.Math.Vectors.Vector3D;
import org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Sensors.SlidesBot_DriveConstants;
import org.firstinspires.ftc.team8109_Rise.Sensors.InertialMeasurementUnit;

@Config
public class Chassis extends MecanumDriveTrain {

    Gamepad gamepad1;
    Telemetry telemetry;

    ElapsedTime runtime = new ElapsedTime();

    public org.firstinspires.ftc.team8109_Rise.Sensors.InertialMeasurementUnit imu;
    public static PIDCoefficients TRANSLATIONAL_PID = new PIDCoefficients(8, 0, 0);
    public static PIDCoefficients HEADING_PID = new PIDCoefficients(0, 0, 0);

    public static double LATERAL_MULTIPLIER = 1.101399867722112;

    public static double VX_WEIGHT = 1;
    public static double VY_WEIGHT = 1;
    public static double ω_WEIGHT = 0.75; //0.75

    double fLeft;
    double fRight;
    double bLeft;
    double bRight;

    double drive;
    double strafe;
    double turn;

    double max;

    double x_rotated;
    double y_rotated;

    // 0.06
    static double translationalX_kp = 0.1;
    static double translationalY_kp = 0.12;
    static double heading_kp = 0.5;

    static double translationalX_ki = 0.002; //0.002
    static double translationalY_ki = 0.002;
    //TODO: Slightly Decrease
    static double heading_ki = 0; //0.05

    // 0.01
    static double translationalX_kd = 0.015;
    static double translationalY_kd = 0.015;
    static double heading_kd = 0.025;

    private static final TrajectoryVelocityConstraint VEL_CONSTRAINT = getVelocityConstraint(SlidesBot_DriveConstants.MAX_VEL, SlidesBot_DriveConstants.MAX_ANG_VEL, SlidesBot_DriveConstants.TRACK_WIDTH);
    private static final TrajectoryAccelerationConstraint ACCEL_CONSTRAINT = getAccelerationConstraint(SlidesBot_DriveConstants.MAX_ACCEL);

    Vector3D controllerInput = new Vector3D(0, 0, 0);

    public PIDF_Controller TranslationalPID_X;
    public PIDF_Controller TranslationalPID_Y;
    public PIDF_Controller HeadingPID;

    Vector3D PID_Vector = new Vector3D(0, 0, 0);

    public OdometryLocalizer odometry;

    public Chassis(Gamepad gamepad1, Telemetry telemetry, HardwareMap hardwareMap){
        super("fLeft", "fRight", "bRight", "bLeft",
                SlidesBot_DriveConstants.kV, SlidesBot_DriveConstants.kA, SlidesBot_DriveConstants.kStatic,
                SlidesBot_DriveConstants.TRACK_WIDTH, SlidesBot_DriveConstants.WHEEL_BASE, LATERAL_MULTIPLIER,
                TRANSLATIONAL_PID, HEADING_PID, VX_WEIGHT, VY_WEIGHT, ω_WEIGHT,
                VEL_CONSTRAINT, ACCEL_CONSTRAINT, hardwareMap);

        reset();

        // TODO: Tune properly (needs some derivative
        TranslationalPID_X = new PIDF_Controller(translationalX_kp, translationalX_kd, 0,translationalX_ki);
        TranslationalPID_Y = new PIDF_Controller(translationalY_kp, translationalY_kd, 0,translationalY_ki);
        HeadingPID = new PIDF_Controller(heading_kp, heading_kd, 0, heading_ki);

        TranslationalPID_X.tolerance = 0.25;
        TranslationalPID_Y.tolerance = 0.25;

        odometry = new OdometryLocalizer(hardwareMap);

        frontLeft.setDirectionReverse();
        backLeft.setDirectionReverse();

        setLocalizer(odometry);
        imu = new InertialMeasurementUnit(hardwareMap);

        this.gamepad1 = gamepad1;
        this.telemetry = telemetry;
    }

    public void setDriveVectorsRobotCentric(Vector3D input){
        //TODO: Test cube weighting
        // Inverse Kinematics Calculations
        fLeft = VX_WEIGHT * input.A - VY_WEIGHT * input.B + ω_WEIGHT * input.C;
        fRight = VX_WEIGHT * input.A + VY_WEIGHT * input.B - ω_WEIGHT * input.C;
        bRight = VX_WEIGHT * input.A - VY_WEIGHT * input.B - ω_WEIGHT * input.C;
        bLeft = VX_WEIGHT * input.A + VY_WEIGHT * input.B + ω_WEIGHT * input.C;

        max = Math.max(Math.max(Math.abs(fLeft), Math.abs(fRight)), Math.max(Math.abs(bLeft), Math.abs(bRight)));
        if (max > 1.0) {
            fLeft /= max;
            fRight /= max;
            bLeft /= max;
            bRight /= max;
        }

        setPower(fLeft, fRight, bRight, bLeft);
    }

    // Unrefined field centric code
    //TODO: Tune heading a bit more
    public void setDriveVectorsFieldCentric(Vector3D input){
        //TODO: Use IMU possibly
        x_rotated = input.A * Math.cos(getPoseEstimate().getHeading()) - input.B * Math.sin(getPoseEstimate().getHeading());
        y_rotated = input.A * Math.sin(getPoseEstimate().getHeading()) + input.B * Math.cos(getPoseEstimate().getHeading());

        fLeft = VX_WEIGHT * x_rotated + VY_WEIGHT * y_rotated + ω_WEIGHT * input.C;
        fRight = VX_WEIGHT * x_rotated - VY_WEIGHT * y_rotated - ω_WEIGHT * input.C;
        bRight = VX_WEIGHT * x_rotated + VY_WEIGHT * y_rotated - ω_WEIGHT * input.C;
        bLeft = VX_WEIGHT * x_rotated - VY_WEIGHT * y_rotated + ω_WEIGHT * input.C;

        max = Math.max(Math.max(Math.abs(fLeft), Math.abs(fRight)), Math.max(Math.abs(bLeft), Math.abs(bRight)));
        if (max > 1.0) {
            fLeft /= max;
            fRight /= max;
            bLeft /= max;
            bRight /= max;
        }

        setPower(fLeft, fRight, bRight, bLeft);
    }

    //TODO: Have kevin teach odo inverse kinematics

    public void  robotRelative(Pose2d powers) {
        setWeightedDrivePower(powers);
    }
//    public void fieldRelative(Pose2d powers) {
//
//        Vector2d vec = new Vector2d(powers.getX(),-powers.getY());
//
//        vec = vec.rotated(getPoseEstimate().getHeading());
//        powers = new Pose2d(vec.getX(),-vec.getY(),powers.getHeading());
//        robotRelative(powers);
//    }

    // Test this out again in teleOp (DriveTesting class)
    public void fieldRelative(Vector3D v) {
        Pose2d pose = new Pose2d(v.A, v.B, v.C);

        Vector2d vec = new Vector2d(pose.getX(),-pose.getY());
//        pose = new Pose2d(vec.getX(),-vec.getY(),-pose.getHeading());

//        vec = vec.rotated(getPoseEstimate().getHeading());
//        pose = new Pose2d(vec.getX(),-vec.getY(), pose.getHeading());

//        if (getPoseEstimate().getHeading() > Math.PI){
//            vec = vec.rotated(-(getPoseEstimate().getHeading()-180));
//            pose = new Pose2d(vec.getX(),-vec.getY(), -(getPoseEstimate().getHeading()-180));
//        }else {
//            vec = vec.rotated(getPoseEstimate().getHeading());
//            pose = new Pose2d(vec.getX(),-vec.getY(), pose.getHeading());
//        }

        vec = vec.rotated(angleWrap(getPoseEstimate().getHeading()));
        pose = new Pose2d(vec.getX(),-vec.getY(), -angleWrap(pose.getHeading()));

        robotRelative(pose);
    }

    public void goToPose(Vector3D input){
        drive = -TranslationalPID_X.PIDF_Power(getPoseEstimate().getX(), input.A);
        strafe = -TranslationalPID_Y.PIDF_Power(getPoseEstimate().getY(), input.B);
        turn = -HeadingPID.PIDF_Power(angleWrap(getPoseEstimate().getHeading()), input.C);

        PID_Vector.set(drive, strafe, turn);

        fieldRelative(PID_Vector);
    }

    //TODO: Test out field-centric
    public void ManualDrive(){
        controllerInput.set(Math.pow(gamepad1.left_stick_y, 3), Math.pow(gamepad1.left_stick_x, 3), Math.pow(gamepad1.right_stick_x, 3));
//        controllerInput.set(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        setDriveVectorsRobotCentric(controllerInput);
    }

    public void DPad_Drive(){
        if (gamepad1.dpad_up){
            drive = 0.25;
        } else if (gamepad1.dpad_down){
            drive = -0.25;
        } else drive = 0;

        if (gamepad1.dpad_left){
            strafe = 0.25;
        } else if (gamepad1.dpad_right){
            strafe = -0.25;
        } else {
            strafe = 0;
        }

        controllerInput.set(drive, strafe, gamepad1.right_stick_x);
//        controllerInput.set(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        setDriveVectorsFieldCentric(controllerInput);
    }

    public void fieldCentricTest(){
        controllerInput.set(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
//        setDriveVectorsFieldCentric(controllerInput);
        fieldRelative(controllerInput);
    }
}
