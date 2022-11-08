package org.firstinspires.ftc.team8110_Invicta.Hardware.Pipelines;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class ColorPipeline extends OpenCvPipeline {
    private static final Point TOPRIGHT = new Point(277.74, 378.24);
    private static final Point BOTTOMLEFT = new Point(350.19, 402.24);

    private static final Scalar RED = new Scalar(255, 0, 0);
    private static final Scalar GREEN = new Scalar(0, 255, 0);
    private static final Scalar BLUE = new Scalar(0, 0, 255);

    private Mat Region, HSV;
    private static Scalar avg;
    private static String color;

    @Override
    public Mat processFrame(Mat input) {
        Region = input.submat(new Rect(BOTTOMLEFT, TOPRIGHT));

        avg = Core.mean(Region);

        Imgproc.cvtColor(input, HSV, Imgproc.COLOR_RGB2HSV);

        Imgproc.rectangle(
                input,
                BOTTOMLEFT,
                TOPRIGHT,
                RED,
                2
        );

        return input;
    }

}
