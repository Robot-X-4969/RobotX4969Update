package org.firstinspires.ftc.teamcode.libs.drive;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.teamcode.libs.components.MotorWrapper;
import org.firstinspires.ftc.teamcode.libs.components.Pinpoint;
import org.firstinspires.ftc.teamcode.libs.core.BaseOpMode;
import org.firstinspires.ftc.teamcode.libs.core.Module;

public class MecanumDrive extends Module {

    private final MotorWrapper frontLeft;
    private final MotorWrapper frontRight;
    private final MotorWrapper backLeft;
    private final MotorWrapper backRight;
    private final Pinpoint pinpoint;

    private boolean isOrientationMode = false;

    private double x;
    private double y;
    private double r;

    public MecanumDrive(BaseOpMode opMode) {

        super(opMode);

        frontLeft = new MotorWrapper(opMode, "frontLeft");
        frontRight = new MotorWrapper(opMode,"frontRight");
        backLeft = new MotorWrapper(opMode, "backLeft");
        backRight = new MotorWrapper(opMode, "backRight");

        pinpoint = new Pinpoint(
                opMode,
                "pinpoint",
                GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD,
                0,
                0
                );
        pinpoint.setStartingPose(0, 0, 0);

    }

    @Override
    protected void init() {

    }

    @Override
    protected void init_loop() {

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
    protected void loop() {

        pinpoint.update();

        double globalAngle = pinpoint.getHeading();

        double xPrime = x * Math.cos(globalAngle) - y * Math.sin(globalAngle);
        double yPrime = -y * Math.cos(globalAngle) - x * Math.sin(globalAngle);

        double frontRightPower = yPrime + xPrime + r;
        double frontLeftPower = yPrime - xPrime - r;
        double backRightPower = yPrime - xPrime + r;
        double backLeftPower = yPrime + xPrime - r;

        double maxPower = Math.max(Math.max(Math.abs(frontRightPower), Math.abs(frontLeftPower)), Math.max(Math.abs(backRightPower), Math.abs(backLeftPower)));

        frontRight.setPower(maxPower > 1.0 ? frontRightPower / maxPower : frontRightPower);
        frontLeft.setPower(maxPower > 1.0 ? frontLeftPower / maxPower : frontLeftPower);
        backRight.setPower(maxPower > 1.0 ? backRightPower / maxPower : backRightPower);
        backLeft.setPower(maxPower > 1.0 ? backLeftPower / maxPower : backLeftPower);

    }

    @Override
    protected void start() {

    }

    @Override
    protected void stop() {

    }

    public Pinpoint getPinpoint() {

        return pinpoint;

    }

}