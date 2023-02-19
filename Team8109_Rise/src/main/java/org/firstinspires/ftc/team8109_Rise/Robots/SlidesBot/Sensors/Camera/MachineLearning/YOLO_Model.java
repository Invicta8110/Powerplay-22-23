package org.firstinspires.ftc.team8109_Rise.Robots.SlidesBot.Sensors.Camera.MachineLearning;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.team8109_Rise.Sensors.Camera.MachineLearning.TensorFlowModel;

public class YOLO_Model extends TensorFlowModel {

    public static final String[] LABELS = {
            "Arturia",
            "Rin",
            "Sakura"
    };

    public YOLO_Model(Telemetry telemetry, HardwareMap hardwareMap) {
        super("FATE_WAIFU_CONE_MODEL", LABELS, telemetry, hardwareMap);
    }
}
