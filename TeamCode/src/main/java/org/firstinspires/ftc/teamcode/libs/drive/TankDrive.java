package org.firstinspires.ftc.teamcode.libs.drive;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.libs.components.MotorWrapper;
import org.firstinspires.ftc.teamcode.libs.components.Pinpoint;
import org.firstinspires.ftc.teamcode.libs.core.BaseOpMode;
import org.firstinspires.ftc.teamcode.libs.core.Module;
import org.firstinspires.ftc.teamcode.libs.util.LogEntry;
import org.firstinspires.ftc.teamcode.libs.util.PIDFController;

public final class TankDrive extends Module {

    private final MotorWrapper frontLeftMotor;
    private final MotorWrapper frontRightMotor;
    private final MotorWrapper backLeftMotor;
    private final MotorWrapper backRightMotor;

    //private final Pinpoint pinpoint;
    private final PIDFController leftpidfController;
    private final PIDFController rightpidfController;
    private final int maxMotorRPM;
    private double throttle;
    private double steering;

    private int targetLeftRPM;
    private int targetRightRPM;

    public TankDrive(BaseOpMode opMode, int maxMotorRPM){

        super(opMode);

        this.frontLeftMotor = new MotorWrapper(opMode, "frontLeftMotor");
        this.frontRightMotor = new MotorWrapper(opMode,"frontRightMotor");
        this.backLeftMotor = null;
        this.backRightMotor = null;

        /*
        this.pinpoint = new Pinpoint(opMode,
                 "pinpoint",
                                GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD,
                                GoBildaPinpointDriver.EncoderDirection.FORWARD,
                                GoBildaPinpointDriver.EncoderDirection.FORWARD,
                         0,
                         0);
        */

        this.maxMotorRPM = maxMotorRPM;

        this.leftpidfController = PIDFController.createPFController(opMode, 0.001, 0.0032);
        this.rightpidfController = PIDFController.createPFController(opMode, 0.001 , 0.0032);

    }

    @Override
    protected void init() {

        frontLeftMotor.setMotorMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeftMotor.setReverse(true);
        frontRightMotor.setMotorMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    protected void init_loop() {

    }

    @Override
    protected void start() {

    }

    @Override
    protected void control_loop() {

        throttle = opMode.getDriverStation().getGamepad1().getLeftStickY();
        steering = opMode.getDriverStation().getGamepad1().getRightStickX();

    }

    @Override
    protected void loop() {

        double targetLeftRPM = (maxMotorRPM * (-throttle + -steering));
        double targetRightRPM = (maxMotorRPM * (-throttle - -steering));

        leftpidfController.setTargetValue(targetLeftRPM, frontLeftMotor.getRPM()/537.7 * 60);
        rightpidfController.setTargetValue(targetRightRPM, frontRightMotor.getRPM()/537.7 * 60);

        frontLeftMotor.setPower(leftpidfController.calculateOutput(frontLeftMotor.getRPM()/537.7 * 60, 0.1));
        frontRightMotor.setPower(rightpidfController.calculateOutput(frontRightMotor.getRPM()/537.7 * 60, 0.1));

        opMode.logData(new LogEntry(LogEntry.EntryType.DEBUG, Double.toString(frontLeftMotor.getRPM()/537.7 * 60), "leftPID:"));
        opMode.logData(new LogEntry(LogEntry.EntryType.DEBUG, Double.toString(frontRightMotor.getRPM()/537.7 * 60), "rightPID:"));
        opMode.logData(new LogEntry(LogEntry.EntryType.DEBUG, Double.toString(throttle), "Throttle:"));
        opMode.logData(new LogEntry(LogEntry.EntryType.DEBUG, Double.toString(steering), "Steering:"));

    }

    @Override
    protected void stop() {

        frontLeftMotor.stop();
        frontRightMotor.stop();

    }

}
