package org.firstinspires.ftc.team8110_Invicta.Control.Autonomous;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvPipeline;

public class ConeDetector extends OpenCvPipeline {
    public ConeDetector() {}

    Point MIDDLE_POINT;
    int middleWidth = 106;
    int middleHeight = 106;

    @Override
    public Mat processFrame(Mat input) {
        return null;
    }
}