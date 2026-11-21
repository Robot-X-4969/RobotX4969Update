package org.firstinspires.ftc.teamcode.libs.drive;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.teamcode.libs.components.Pinpoint;
import org.firstinspires.ftc.teamcode.libs.core.BaseOpMode;
import org.firstinspires.ftc.teamcode.libs.core.Module;

public class MecanumDrive extends Module {

    //private final MotorWrapper frontLeft;
    //private final MotorWrapper frontRight;
    //private final MotorWrapper backLeft;
    //private final MotorWrapper backRight;
    private final Pinpoint pinpoint;

    private boolean isOrientationMode = false;

    private double x;
    private double y;
    private double r;

    public MecanumDrive(RobotTeleOp opMode) {

        super(opMode);

        //frontLeft = new MotorWrapper(opMode, "frontLeft");
        //frontRight = new MotorWrapper(opMode,"frontRight");
        //backLeft = new MotorWrapper(opMode, "backLeft");
        //backRight = new MotorWrapper(opMode, "backRight");

        pinpoint = new Pinpoint(
                opMode,
                "pinpoint",
                GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD,
                0,
                0
                );

    }

    @Override
    protected void init_loop() {

    }

    @Override
    protected void loop() {

        control_loop();

        if(opMode.isInDebugMode()){

            pinpoint.getDebugValues();

        }




    }

    @Override
    protected void control_loop() {

        if(opMode.getDriverStation().getGamepad1().getY().isPressed()){

            isOrientationMode = !isOrientationMode;

        }

        x = opMode.getDriverStation().getGamepad1().getLeftStickX();
        y = opMode.getDriverStation().getGamepad1().getLeftStickY();
        r = opMode.getDriverStation().getGamepad1().getRightStickX();

    }

    @Override
    protected void init() {

    }

    @Override
    protected void start() {

    }

    @Override
    protected void stop() {

    }
}