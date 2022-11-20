package org.firstinspires.ftc.team8110_Invicta.Hardware.Pipelines;

import org.firstinspires.ftc.team8110_Invicta.Hardware.States.Colors;
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

    private static final Scalar WHITE = new Scalar(255, 255, 255);

    private Mat Region;
    private Mat HSV = new Mat();
    private static Scalar avg;
    private static double hue;
    private static String color;

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, HSV, Imgproc.COLOR_RGB2HSV);

        Region = HSV.submat(new Rect(BOTTOMLEFT, TOPRIGHT));

        avg = Core.mean(Region);
        hue = avg.val[0];

        Imgproc.rectangle(
                input,
                BOTTOMLEFT,
                TOPRIGHT,
                WHITE,
                2
        );

        return HSV;
    }

    public Colors findColor() {
        if ((hue >= 165 && hue <= 179) || (hue <= 7 && hue >= 0))
            return Colors.RED;
        else if (hue >= 41 && hue <= 65)
            return Colors.GREEN;
        else if (hue >= 100 && hue <= 135)
            return Colors.BLUE;
        else
            return Colors.UNKNOWN;
    }

    //@return: the hue!!
    public double getHue() {
        return hue;
    }
}

