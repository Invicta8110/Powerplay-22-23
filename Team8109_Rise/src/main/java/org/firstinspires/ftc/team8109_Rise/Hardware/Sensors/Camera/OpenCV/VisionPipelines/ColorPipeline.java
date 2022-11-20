package org.firstinspires.ftc.team8109_Rise.Hardware.Sensors.Camera.OpenCV.VisionPipelines;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class ColorPipeline extends OpenCvPipeline {
    private static final Point TOPRIGHT = new Point(30, 30);
    private static final Point BOTTOMLEFT = new Point(50, 50);

    private static final Scalar WHITE = new Scalar(255, 255, 255);

    Telemetry telemetry;
    private Mat Region = new Mat();
    private Mat HSV = new Mat();
    private static Scalar avg;
    private static double hue;
    private static String color;

    public enum Colors{
            RED,
            GREEN,
            BLUE,
            UNKNOWN
    }

    public ColorPipeline(Telemetry telemetry){
        this.telemetry = telemetry;
    }

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, HSV, Imgproc.COLOR_RGB2HSV);

        Region = HSV.submat(new Rect(BOTTOMLEFT, TOPRIGHT));

        avg = Core.mean(Region);
        hue = avg.val[0];

        Imgproc.rectangle(
                HSV,
                BOTTOMLEFT,
                TOPRIGHT,
                WHITE,
                2
        );

        telemetry.addData("Hue", getHue());
        telemetry.update();
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
