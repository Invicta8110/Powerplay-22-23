package org.firstinspires.ftc.team8109_Rise.Control;

import com.qualcomm.robotcore.util.ElapsedTime;

public class PIDF_Controller {

    public ElapsedTime runtime = new ElapsedTime();

    public double tolerance;

    double kp;
    double kd;
    double ki;
    double a;
    double F;
    public double error;

    public double area;

    public double P = 0;
    public double I = 0;
    public double D = 0;

    public double deltaTime;
    public double previousError = 0;
    public double previousTarget = 0;

    public double previousFilterEstimate = 0;
    public double currentFilterEstimate = 0;

    public double errorChange;

    public PIDF_Controller(double kp){
        this.kp = kp;

        a = 0;
        kd = 0;
        ki = 0;
        F = 0;
    }

    public PIDF_Controller(double kp, double kd){
        this.kp = kp;
        this.kd = kd;

        a = 0;
        ki = 0;
        F = 0;
    }

    public PIDF_Controller(double kp, double kd, double a){
        this.kp = kp;
        this.kd = kd;
        this.a = a;

        ki = 0;
        F = 0;
    }

    public PIDF_Controller(double kp, double kd, double a, double ki){
        this.kp = kp;
        this.kd = kd;
        this.a = a;
        this.ki = ki;

        F = 0;
    }

    public PIDF_Controller(double kp, double kd, double a, double ki, double F){
        this.kp = kp;
        this.kd = kd;
        this.a = a;
        this.ki = ki;
        this.F = F;
    }

    /***
     * How to tune PID controller:
     * - Start with tuning kp
     *      - This can be done roughly mathematically and then fine-tuned for more stable/predictable systems
     * - Next tune ki or kd
     *      - If your mechanism is accelerating too fast, tune kd and if it's not reaching the target then tune ki
     * - For tuning either ki or kd, first set it to a very small number and record the telemetry data of what Integral and Derivative
     *   outputs are (the I and D variables) and then increase it the proper amount of decimal places to have a meaningful effect on the
     *   system. Then fine-tune it until it more or less performs how you would like it to.
     */
    public double PIDF_Power(double currPos, double targetPos){
        error = targetPos - currPos;
        errorChange = error - previousError;

        // Proportional term : KP constant * the error of the system
        P = kp*error;

        deltaTime = runtime.seconds();
        runtime.reset();

        if (Math.abs(error) > tolerance) area += ((error+previousError)*deltaTime)/2;
        if (targetPos != previousTarget) area = 0;

        I = area*ki;

        currentFilterEstimate = (1-a) * errorChange + (a * previousFilterEstimate);

        D = kd * (currentFilterEstimate / deltaTime);

        previousError = error;
        previousFilterEstimate = currentFilterEstimate;
        previousTarget = targetPos;

        return P + I + D + F;
    }
}
