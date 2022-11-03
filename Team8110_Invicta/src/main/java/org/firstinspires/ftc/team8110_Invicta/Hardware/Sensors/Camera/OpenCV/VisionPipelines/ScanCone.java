package org.firstinspires.ftc.team8110_Invicta.Hardware.Sensors.Camera.OpenCV.VisionPipelines;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class ScanCone extends OpenCvPipeline {
    @Override
    public Mat processFrame(Mat input) {
        Mat mat = new Mat();
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2RGBA);
        return mat;
    }

}
