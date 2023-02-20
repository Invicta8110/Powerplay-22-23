package org.firstinspires.ftc.team8110_Invicta.Hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.team8110_Invicta.Hardware.Drivetrains.StraferChassis;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.Motor;
import org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms.ScissorLift;

public class AAAAAAA extends StraferChassis {
    HardwareMap hwmap;
    Lift lift;

    public AAAAAAA(HardwareMap hardwareMap) {
        super(hardwareMap);
        this.hwmap = hardwareMap;
        this.lift = new Lift("lift");
    }

    public class Lift implements ScissorLift {
        private Motor liftMotor;

        public Lift(String name) {
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
}
