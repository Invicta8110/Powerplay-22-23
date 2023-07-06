package org.firstinspires.ftc.team8110_Invicta.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team8110_Invicta.Hardware.ElefanteMcNuggets;

import ftc.rogue.blacksmith.Anvil;
import ftc.rogue.blacksmith.BlackOp;
import ftc.rogue.blacksmith.Scheduler;
import ftc.rogue.blacksmith.annotations.CreateOnGo;
import ftc.rogue.blacksmith.listeners.ReforgedGamepad;

@TeleOp
public class ReforgedTeleOp extends BlackOp {
    ElefanteMcNuggets robot;

    @Override
    public void go() {
        robot = new ElefanteMcNuggets(hardwareMap);

        ReforgedGamepad gamepad = this.getReforgedGamepad1();

        gamepad.a.onJustTrue(robot.getClaw()::close);
        gamepad.y.onJustTrue(robot.getClaw()::open);

        gamepad.left_bumper.onRise(() -> robot.getLift().power(-1))
            .onFall(() -> robot.getLift().power(0));
        gamepad.right_bumper.onRise(() -> robot.getLift().power(1))
            .onFall(() -> robot.getLift().power(0));

        gamepad.dpad_up.onRise(() -> robot.getLift().power(-1))
            .onFall(() -> robot.getLift().power(0));
        gamepad.dpad_down.onRise(() -> robot.getLift().power(1))
            .onFall(() -> robot.getLift().power(0));

        Scheduler.launchOnStart(this, () -> {
            robot.setDrivePower(gamepad.left_stick_y.get(), gamepad.left_stick_x.get(), gamepad.right_stick_x.get());

            mTelemetry().addData("Y Position", gamepad.left_stick_y.get());
            mTelemetry().update();
        });
    }
}
