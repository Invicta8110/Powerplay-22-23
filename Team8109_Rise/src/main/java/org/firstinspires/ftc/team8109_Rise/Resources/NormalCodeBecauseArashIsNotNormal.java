package org.firstinspires.ftc.team8109_Rise.Resources;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class NormalCodeBecauseArashIsNotNormal extends LinearOpMode {

    NormalCodeBecauseArashIsNotNormalH robot = new NormalCodeBecauseArashIsNotNormalH();
    ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        double vacSpeed = 0;

        double rotateRLSpeed;
        double rotateUDSpeed;
        robot.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()){
            telemetry.addLine("Vacuum Speed: " + vacSpeed);

            if(gamepad1.a) {
                vacSpeed = 1;
            }
            else {
                vacSpeed = 0.0;
            }


            rotateRLSpeed = gamepad1.left_stick_x;
            rotateUDSpeed = gamepad1.left_stick_y;

            robot.vacLeft.setPower(-vacSpeed);
            robot.vacRight.setPower(vacSpeed);

            robot.rotateRL.setPower(rotateRLSpeed);
            robot.rotateUD.setPower(rotateUDSpeed);
        }
    }
}
