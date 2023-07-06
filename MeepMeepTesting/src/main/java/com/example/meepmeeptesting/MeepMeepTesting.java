package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(34,-58, Math.toRadians(87))).setTangent(Math.toRadians(87))
                            .lineToSplineHeading(new Pose2d(24.4,-3.2, Math.toRadians(95)))
                            .lineToSplineHeading(new Pose2d(66.8,-11.6, Math.toRadians(-9)))
                            .lineToSplineHeading(new Pose2d(28,-1.2, Math.toRadians(168)))
                            .lineToSplineHeading(new Pose2d(61.6,-34.8, Math.toRadians(-39)))
                            .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_KAI_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
