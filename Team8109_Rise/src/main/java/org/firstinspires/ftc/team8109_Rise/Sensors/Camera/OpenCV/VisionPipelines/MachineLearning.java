package org.firstinspires.ftc.team8109_Rise.Sensors.Camera.OpenCV.VisionPipelines;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.WorkerThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import org.firstinspires.ftc.robotcore.internal.system.RefCounted;
import org.firstinspires.ftc.team8109_Rise.Sensors.Camera.ControlHub;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.dnn.Dnn;
import org.opencv.dnn.Net;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MachineLearning extends OpenCvPipeline {
    private static final int TARGET_IMG_WIDTH = 224;
    private static final int TARGET_IMG_HEIGHT = 224;

    private static final double SCALE_FACTOR = 1 / 255.0;

//    Telemetry telemetry;

    // load the OpenCV native library
    private Net net;
    ControlHub hub;
    final int IN_WIDTH = 300;
    final int IN_HEIGHT = 300;
    final float WH_RATIO = (float)IN_WIDTH / IN_HEIGHT;
    final double IN_SCALE_FACTOR = 0.007843;
    final double MEAN_VAL = 127.5;
    final double THRESHOLD = 0.6;

    public MachineLearning(){
//        new Handler(Looper.getMainLooper()).post(new Runnable() {
//            @Override
//            public void run() {
//                // write your code here
//                hub = new ControlHub();
//            }
//        });

//        try {
//            AssetManager assetManager = AppUtil.getDefContext().getAssets();
//            AssetFileDescriptor afd = assetManager.openFd("WaifuConeBest.onnx");
////            try (FileInputStream fis = afd.createInputStream()) {
//////                initialize(fis, afd.getStartOffset(), afd.getDeclaredLength(), classNames);
////                MappedByteBuffer modelData = fis.getChannel().map(FileChannel.MapMode.READ_ONLY, afd.getStartOffset(), afd.getDeclaredLength());
////            }
////            MappedByteBuffer modelData = fis.getChannel().map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
//
//            String weights = afd.toString();
//            net = Dnn.readNet(weights);
//        } catch (IOException e) {
//            throw new RuntimeException("loadModelFromAsset failed", e);
//        }

//        String weights =  "WaifuConeBest.onnx";

//        String weights = "/sdcard/FIRST/WaifuConeBest.onnx";
//        this.telemetry = telemetry;
    }

//    @WorkerThread
//    void workerThread() {
//        ContextCompat.getMainExecutor(hub).execute(()  -> {
//            // This is where your UI code goes.
//        }
//    }

//    activity.runOnUiThread(new Runnable() {
//        public void run() {
//            Toast.makeText(activity, "Hello", Toast.LENGTH_SHORT).show();
//        }
//    });


    @Override
    public Mat processFrame(Mat input) {

        // Get a new frame
//        Imgproc.cvtColor(input, input, Imgproc.COLOR_RGBA2RGB);
//        // Forward image through network.
//        Mat blob = Dnn.blobFromImage(input, IN_SCALE_FACTOR, new Size(IN_WIDTH, IN_HEIGHT), new Scalar(MEAN_VAL, MEAN_VAL, MEAN_VAL), /*swapRB*/false, /*crop*/false);
//        net.setInput(blob);
//        Mat detections = net.forward();
//        int cols = input.cols();
//        int rows = input.rows();
//        detections = detections.reshape(1, (int)detections.total() / 7);
//        for (int i = 0; i < detections.rows(); ++i) {
//            double confidence = detections.get(i, 2)[0];
//            if (confidence > THRESHOLD) {
//                int classId = (int)detections.get(i, 1)[0];
//                int left   = (int)(detections.get(i, 3)[0] * cols);
//                int top    = (int)(detections.get(i, 4)[0] * rows);
//                int right  = (int)(detections.get(i, 5)[0] * cols);
//                int bottom = (int)(detections.get(i, 6)[0] * rows);
//                // Draw rectangle around detected object.
//                Imgproc.rectangle(input, new Point(left, top), new Point(right, bottom),
//                        new Scalar(0, 255, 0));
//                String label = classNames[classId] + ": " + confidence;
//                int[] baseLine = new int[1];
//                Size labelSize = Imgproc.getTextSize(label, Imgproc.FONT_HERSHEY_SIMPLEX, 0.5, 1, baseLine);
//                // Draw background for label.
//                Imgproc.rectangle(input, new Point(left, top - labelSize.height),
//                        new Point(left + labelSize.width, top + baseLine[0]),
//                        new Scalar(255, 255, 255), Imgproc.FILLED);
//                // Write class name and confidence.
//                Imgproc.putText(input, label, new Point(left, top), Imgproc.FONT_HERSHEY_SIMPLEX, 0.5, new Scalar(0, 0, 0));
//
////                telemetry.addData("Waifu Detected:", label);
//            }
//        }
//        blob.release();
        return input;
    }

    // Upload file to storage and return a path.
//    private static String getPath(String file, Context context) {
//        AssetManager assetManager = context.getAssets();
//        BufferedInputStream inputStream = null;
//        try {
//            // Read data from assets.
//            inputStream = new BufferedInputStream(assetManager.open(file));
//            byte[] data = new byte[inputStream.available()];
//            inputStream.read(data);
//            inputStream.close();
//
////            Looper.prepare();
//            // Create copy file in storage.
//            File outFile = new File(context.getFilesDir(), file);
//            FileOutputStream os = new FileOutputStream(outFile);
//            os.write(data);
//            os.close();
//            // Return a path to file which may be read in common way.
//            return outFile.getAbsolutePath();
//        } catch (IOException ex) {
////            Log.i(TAG, "Failed to upload a file");
//
//        }
//        return "";
//    }
    private static final String[] classNames = {"Rin", "Artoria", "Sakura"};

}
