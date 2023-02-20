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
                    drive.trajectorySequenceBuilder(Pose2d(60.0, 36.0, Math.toRadians(180.0)))
                            .forward(18.0)
                            .turn(Math.toRadians(90.0))
                            .forward(12.0)
                            .turn(Math.toRadians(-90.0))
                            .forward(12.0)
                            .build()

                }


        meepMeep.setBackground(Background.FIELD_POWERPLAY_KAI_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start()

    }


}