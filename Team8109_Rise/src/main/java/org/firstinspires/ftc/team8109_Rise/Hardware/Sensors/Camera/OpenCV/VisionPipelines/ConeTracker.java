package org.firstinspires.ftc.team8109_Rise.Hardware.Sensors.Camera.OpenCV.VisionPipelines;

import static org.opencv.core.Core.inRange;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConeTracker extends OpenCvPipeline {
    Telemetry telemetry;

    static final Scalar GREEN = new Scalar(0, 255, 0);

    boolean RED = true;

    public int redContourCount = 0;

    public List<Rect> redRect;

    public Rect RedRect;

    public List<MatOfPoint> redContours;

    public MatOfPoint biggestContour;
    public ConeTracker(Telemetry telemetry) {
        redContours = new ArrayList<MatOfPoint>();
        redRect = new ArrayList<Rect>();
        RedRect = new Rect();
        biggestContour = new MatOfPoint();

        this.telemetry = telemetry;
    }

    // Filters the contours to be greater than a specific area in order to be tracked
    public boolean filterContours(MatOfPoint contour) {
        return Imgproc.contourArea(contour) > 100;
    }

    // Yellow masking thresholding values:
    Scalar lowRed = new Scalar(0, 170, 0); //10, 100, 50
    Scalar highRed = new Scalar(255, 255, 255); //35, 255, 255

    // Mat object for the yellow mask
    Mat maskRed = new Mat();

    // Mat object for HSV color space
    Mat YCrCb = new Mat();

    // Kernel size for blurring
    Size kSize = new Size(5, 5);
    Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size((2 * 2) + 1, (2 * 2) + 1));

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
        Imgproc.erode(YCrCb, YCrCb, kernel);

        if (RED) {
            // Finds the pixels within the thresholds and puts them in the mat object "maskYellow"
            inRange(YCrCb, lowRed, highRed, maskRed);

            // Clears the arraylists
            redContours.clear();
            redRect.clear();

            // Finds the contours and draws them on the screen
            Imgproc.findContours(maskRed, redContours, new Mat(), Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
            Imgproc.drawContours(input, redContours, -1, GREEN); //input

            // Iterates through each contour
            for (int i = 0; i < redContours.size(); i++) {

                // Filters out contours with an area less than 50 (defined in the filter contours method)
                if (filterContours(redContours.get(i))) {
                    // Creates a bounding rect around each contourand the draws it

                    biggestContour = Collections.max(redContours, (t0, t1) -> {
                        return Double.compare(Imgproc.boundingRect(t0).width, Imgproc.boundingRect(t1).width);
                    });

                    RedRect = Imgproc.boundingRect(biggestContour);
                    redRect.add(Imgproc.boundingRect(redContours.get(i)));
                    Imgproc.rectangle(input, redRect.get(redContourCount), GREEN, 2);
                    // Creates a count for the amount of yellow contours on the the screen
                    redContourCount++;
                }
            }

            telemetry.addData("Yellow Contour Count", redContourCount);

            // Displays the position of the center of each bounding rect (rect.x/y returns the top left position)
            for (int i = 0; i < redRect.size(); i++){
                telemetry.addData("Yellow Contour " + (i+1), "%7d,%7d", redRect.get(i).x + (redRect.get(i).width/2), redRect.get(i).y + (redRect.get(i).height/2));
            }

            maskRed.release();
        }

        redContourCount = 0;

        YCrCb.release();

        return input;
    }
}
