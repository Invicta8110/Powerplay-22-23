package org.firstinspires.ftc.team8110_Invicta.OpModes.Auton;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.team8110_Invicta.Hardware.ElefanteMcNuggets;

import ftc.rogue.blacksmith.Anvil;
import ftc.rogue.blacksmith.BlackOp;
import ftc.rogue.blacksmith.Scheduler;
import ftc.rogue.blacksmith.annotations.CreateOnGo;
import ftc.rogue.blacksmith.units.GlobalUnits;

public class Base extends BlackOp {
    @CreateOnGo(passHwMap = true)
    protected ElefanteMcNuggets robot;

    protected Pose2d startPose;

    Anvil mainTraj(Pose2d startPose) {
        return Anvil.forgeTrajectory(robot, startPose)
            .forward(18);
    }

    Anvil goToHigh(Anvil anvil) {
        return anvil
            .forward(18)
            .turn(45);
    }

    @Override
    public void go() {
        startPose = GlobalUnits.pos(robot.getPoseEstimate());

        Anvil startTraj = mainTraj(startPose);

        Anvil.startAutoWith(startTraj).onSchedulerLaunch();

        Scheduler.launchOnStart(this, () -> {
            robot.update();
        });
    }



}
