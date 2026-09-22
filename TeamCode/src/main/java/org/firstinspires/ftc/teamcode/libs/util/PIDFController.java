package org.firstinspires.ftc.teamcode.libs.util;

import org.firstinspires.ftc.teamcode.libs.core.BaseOpMode;

public final class PIDFController {

    private final BaseOpMode opMode;
    private final double kP;
    private final double kI;
    private final double kD;
    private final double kF;
    private double integral;
    private double previousError;
    private double targetValue;
    private boolean initialTargetValueSet;


    private PIDFController(BaseOpMode opMode, double kP, double kI, double kD, double kF) {

        this.opMode = opMode;
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kF = kF;

        integral = 0;
        previousError = 0;
        targetValue = 0;

    }

    public static PIDFController createPIController(BaseOpMode opMode, double kP, double kI) {

        return new PIDFController(opMode, kP, kI, 0.0, 0.0);

    }

    public static PIDFController createPDController(BaseOpMode opMode, double kP, double kD) {

        return new PIDFController(opMode, kP, 0.0, kD, 0.0);

    }

    public static PIDFController createPIDController(BaseOpMode opMode, double kP, double kI, double kD) {

        return new PIDFController(opMode, kP, kI, kD, 0.0);

    }

    public static PIDFController createPIDFController(BaseOpMode opMode, double kP, double kI, double kD, double kF) {

        return new PIDFController(opMode, kP, kI, kD, kF);

    }

    public static PIDFController createPFController(BaseOpMode opMode, double kP, double kF) {

        return new PIDFController(opMode, kP, 0, 0, kF);

    }

    public double calculateOutput(double currentValue, double deltaTime) {

        if (initialTargetValueSet) {

            double error = targetValue - currentValue;

            integral += error * deltaTime;

            double F = this.kF * targetValue;
            double P = this.kP * error;
            double I = this.kI * integral;
            double D = this.kD * (error - previousError) / deltaTime;

            previousError = error;

            return F + P + I + D;

        }

        opMode.logData(new LogEntry(LogEntry.EntryType.ERROR, "in " + PIDFController.class.getName() + ": target value has not been set before calling calculateOutput", null));
        opMode.requestOpModeStop();

        return 0;

    }

    public void setTargetValue(double targetValue, double currentValue){

        initialTargetValueSet = true;

        this.targetValue = targetValue;

        integral = 0;

        previousError = targetValue - currentValue;

    }

}
