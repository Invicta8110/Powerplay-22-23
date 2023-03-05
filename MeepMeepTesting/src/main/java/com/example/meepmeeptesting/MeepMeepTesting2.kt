package com.example.meepmeeptesting

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.noahbres.meepmeep.MeepMeep
import com.noahbres.meepmeep.MeepMeep.Background
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder
import com.noahbres.meepmeep.roadrunner.DriveShim

object MeepMeepTesting2 {
    @JvmStatic
    fun main(args: Array<String>) {
        val meepMeep = MeepMeep(800)
        val myBot = DefaultBotBuilder(meepMeep) // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60.0, 60.0, Math.toRadians(180.0), Math.toRadians(180.0), 15.0)
                 .followTrajectorySequence { drive: DriveShim ->
                    drive.trajectorySequenceBuilder(Pose2d( 34.4,60.0, Math.toRadians(-90.0)))
                            .strafeRight(21.3)
                            .forward(25.0)
                            .strafeLeft(10.8)
                            .strafeRight(11.0)
                            .back(2.0)
                            .turn(Math.toRadians(180.0))
                            .forward(24.0)
                            .turn(Math.toRadians(45.0))
                            .forward(4.0)
                            .back(4.0)
                            .turn(Math.toRadians(-45.0))
                            .turn(Math.toRadians(180.0))
                            .forward(25.0)
                            .strafeLeft(10.8)
                            .build()

                }


        meepMeep.setBackground(Background.FIELD_POWERPLAY_KAI_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start()

    }



}