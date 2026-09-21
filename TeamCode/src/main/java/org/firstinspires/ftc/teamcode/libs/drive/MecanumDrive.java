package org.firstinspires.ftc.teamcode.libs.drive;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.teamcode.libs.components.MotorWrapper;
import org.firstinspires.ftc.teamcode.libs.components.Pinpoint;
import org.firstinspires.ftc.teamcode.libs.core.BaseOpMode;
import org.firstinspires.ftc.teamcode.libs.core.Module;


public final class MecanumDrive extends Module {

    private final MotorWrapper frontLeftMotor;
    private final MotorWrapper frontRightMotor;
    private final MotorWrapper backLeftMotor;
    private final MotorWrapper backRightMotor;
    private final Pinpoint pinpoint;

    private boolean isOrientationMode;

    public MecanumDrive(BaseOpMode opMode) {

        super(opMode);

        frontLeftMotor = new MotorWrapper(opMode, "frontLeftMotor");
        frontRightMotor = new MotorWrapper(opMode,"frontRightMotor");
        backLeftMotor = new MotorWrapper(opMode,"backLeftMotor");
        backRightMotor = new MotorWrapper(opMode,"backRightMotor");

        pinpoint = new Pinpoint(opMode, "pinpoint", GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD, GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD, 0, 0);

        frontLeftMotor.setReverse(false);
        backLeftMotor.setReverse(false);
        frontRightMotor.setReverse(false);
        backRightMotor.setReverse(false);

    }

    @Override
    public void init() {



    }

    @Override
    public void init_loop() {



    }

    @Override
    public void start() {

    }

    @Override
    public void control_loop() {




    }

    @Override
    public void loop() {

    }

    @Override
    public void stop() {

        frontLeftMotor.stop();
        backLeftMotor.stop();
        frontRightMotor.stop();
        backRightMotor.stop();

    }

}
