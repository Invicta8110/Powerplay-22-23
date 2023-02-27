package org.firstinspires.ftc.team8110_Invicta.Hardware;

import static org.firstinspires.ftc.team8110_Invicta.Hardware.States.DriveSpeeds.NORMAL;
import static org.firstinspires.ftc.team8110_Invicta.Hardware.States.DriveSpeeds.SLOW;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team8110_Invicta.Hardware.Drivetrains.StraferChassis;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.Claw;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.Lift;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.Motor;
import org.firstinspires.ftc.team8110_Invicta.Hardware.States.DriveSpeeds;

public class ElefanteDrugMcNuggets extends StraferChassis {
    HardwareMap hwmap;
    ScissorLift lift;

    public ScissorLift getLift() {
        return lift;
    }

    public ConeClaw getClaw() {
        return claw;
    }

    ConeClaw claw;
    DriveSpeeds speed = NORMAL;
    int diff = 1;

    public ElefanteDrugMcNuggets(HardwareMap hardwareMap) {
        super(hardwareMap);
        this.hwmap = hardwareMap;
        this.lift = new ScissorLift("lift");
        this.claw = new ConeClaw("claw");
    }

    public class ScissorLift implements Lift {
        private Motor liftMotor;

        public ScissorLift(String name) {
            liftMotor = new Motor(name, hwmap);
        }

        @Override
        public void move(double power) {
            liftMotor.setPower(power);
        }

        @Override
        public double getPosition() {
            return liftMotor.getCurrentPosition();
        }

        @Override
        public double getInches() {
            return liftMotor.getCurrPosInches();
        }

        @Override
        public void upLevel() {
            liftMotor.runToPosition((int) (getPosition() + 160));
        }

        @Override
        public void downLevel() {
            liftMotor.runToPosition((int) (getPosition() - 160));
        }
    }

    public class ConeClaw implements Claw {
        private Servo servo;

        public ConeClaw(String clawName) {
            servo = hwmap.get(Servo.class, clawName);
        }

        @Override
        public void open() {
            servo.setPosition(.8);
        }

        @Override
        public void close() {
            servo.setPosition(.9);
        }

        @Override
        public double getPosition() {
            return servo.getPosition();
        }
    }

    public void teleOpDrive(Gamepad gamepad) {
        switch (speed) {
            case NORMAL:
                if (gamepad.x) {
                    diff = 3;
                    speed = SLOW;
                }
                break;
            case SLOW:
                if (gamepad.x) {
                    diff = 2;
                    speed = NORMAL;
                }
                break;
        }

        this.setWeightedDrivePower(new Pose2d(
            gamepad.left_stick_y/diff,
            gamepad.right_stick_x/diff,
            gamepad.left_stick_x/diff
        ));
    }

    public void teleOpClaw(Gamepad gamepad) {
        if (gamepad.a) {
            claw.open();
        } else if (gamepad.y) {
            claw.close();
        }
    }

    public void teleOpLift(Gamepad gamepad) {
        double up = gamepad.left_bumper ? 1 : 0;
        double down = gamepad.right_bumper ? 1 : 0;

        lift.move(up-down);
    }
}
