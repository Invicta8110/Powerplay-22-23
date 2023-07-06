package org.firstinspires.ftc.team8110_Invicta.OpModes.Auton;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import ftc.rogue.blacksmith.Anvil;

public class ParkAuton extends Base {


    @Override
    Anvil mainTraj(Pose2d startPose) {
        return Anvil.forgeTrajectory(robot, startPose);
    }

}
