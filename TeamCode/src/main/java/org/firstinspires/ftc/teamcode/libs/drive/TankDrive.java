package org.firstinspires.ftc.teamcode.libs.drive;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.teamcode.libs.components.MotorWrapper;
import org.firstinspires.ftc.teamcode.libs.components.Pinpoint;
import org.firstinspires.ftc.teamcode.libs.core.BaseOpMode;
import org.firstinspires.ftc.teamcode.libs.core.Module;
import org.firstinspires.ftc.teamcode.libs.util.PIDFController;

public final class TankDrive extends Module {

    private final MotorWrapper frontLeftMotor;
    private final MotorWrapper frontRightMotor;
    private final MotorWrapper backLeftMotor;
    private final MotorWrapper backRightMotor;
    private final Pinpoint pinpoint;
    private final PIDFController pidfController;
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

        this.pinpoint = new Pinpoint(opMode,
                 "pinpoint",
                                GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD,
                                GoBildaPinpointDriver.EncoderDirection.FORWARD,
                                GoBildaPinpointDriver.EncoderDirection.FORWARD,
                         0,
                         0);

        this.maxMotorRPM = maxMotorRPM;

        this.pidfController = PIDFController.createPFController(opMode, 0.0, 0.0);

    }

    @Override
    protected void init() {

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

        targetLeftRPM = throttle * maxMotorRPM + steering;


    }

    @Override
    protected void loop() {



    }



    @Override
    protected void stop() {

    }
}
