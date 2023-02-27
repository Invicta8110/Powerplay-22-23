package org.firstinspires.ftc.team8110_Invicta.Hardware.Pipelines;

import org.firstinspires.ftc.team8110_Invicta.Hardware.States.Colors;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class ColorDetector extends OpenCvPipeline {

    //colors
    static final Scalar GOLD = new Scalar(255,215,0);
    //static final Scalar crimson = new Scalar(220,20,60);
    //Telemetry telemetry;
    Mat mat = new Mat();
    // Mat input;
    public ColorDetector(){
    }

    //initializing the dimensions
    Point MIDDLE_POINT;
    int middleWidth = 106;
    int middleHeight = 106;
    Mat region1;
    public int[] hsv_Value = new int[3];

    @Override
    public Mat processFrame(Mat input) {
        MIDDLE_POINT = new Point(input.cols()/2 - middleWidth/2, input.rows()/2 - middleHeight/2);

        //points that will be used to make a rectangle
        Point cornerA = new Point(MIDDLE_POINT.x, MIDDLE_POINT.y);
        Point cornerB = new Point(MIDDLE_POINT.x + middleWidth, MIDDLE_POINT.y + middleHeight);

        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
        //creates the rect and finds the HSV value
        region1 = mat.submat(new Rect(cornerA, cornerB));

        //loop to update the HSV in the array int[]
        //adds with every update
        for(int i = 0; i < 3; i++){
            hsv_Value[i] = (int) Core.mean(region1).val[i];
        }
        //draw them in the video feed
        Imgproc.rectangle(mat, cornerA, cornerB, GOLD, 1);

        return mat;
    }

    public Colors getColor() {
        int hue = hsv_Value[0];

        if ((hue >= 0 && hue < 30) || (hue >= 301/2 && hue <= 360/2)) {
            return Colors.RED;
        } else if (hue >= 120/2 && hue <= 180/20) {
            return Colors.GREEN;
        } else if (hue >= 210/2 && hue <= 300/2) {
            return Colors.BLUE;
        } else {
            return Colors.UNKNOWN;
        }
    }

}
