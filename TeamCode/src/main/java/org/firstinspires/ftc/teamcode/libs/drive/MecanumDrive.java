package org.firstinspires.ftc.teamcode.libs.drive;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.libs.components.MotorWrapper;
import org.firstinspires.ftc.teamcode.libs.components.Pinpoint;
import org.firstinspires.ftc.teamcode.libs.core.BaseOpMode;
import org.firstinspires.ftc.teamcode.libs.core.Module;

public final class MecanumDrive extends Module {

    private final MotorWrapper frontLeft;
    private final MotorWrapper frontRight;
    private final MotorWrapper backLeft;
    private final MotorWrapper backRight;
    private final Pinpoint pinpoint;
    private double driverOffset;
    private boolean isOrientationMode = false;
    private double x;
    private double y;
    private double r;

    public MecanumDrive(BaseOpMode opMode, Pinpoint pinpoint) {

        super(opMode);

        this.pinpoint = pinpoint;

        frontLeft = new MotorWrapper(opMode, "frontLeft", 0.0);
        frontRight = new MotorWrapper(opMode,"frontRight", 0.0);
        backLeft = new MotorWrapper(opMode, "backLeft", 0.0);
        backRight = new MotorWrapper(opMode, "backRight", 0.0);

    }

    @Override
    protected void init() {

        backLeft.setMotorMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMotorMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setMotorMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMotorMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    @Override
    protected void init_loop() {

    }

    @Override
    protected void start() {

    }

    @Override
    protected void control_loop() {

        if(opMode.getDriverStation().getGamepad1().getY().justPressed()){

            isOrientationMode = !isOrientationMode;

        }

        x = opMode.getDriverStation().getGamepad1().getLeftStickX();
        y = opMode.getDriverStation().getGamepad1().getLeftStickY();
        r = opMode.getDriverStation().getGamepad1().getRightStickX();

    }

    @Override
    protected void loop() {

        pinpoint.update();

        double globalAngle = pinpoint.getHeading(AngleUnit.RADIANS) + driverOffset;

        double xPrime;
        double yPrime;


        if(isOrientationMode){

            xPrime = x * Math.cos(globalAngle) - y * Math.sin(globalAngle);
            yPrime = -y * Math.cos(globalAngle) - x * Math.sin(globalAngle);

        } else {

            xPrime = x;
            yPrime = -y;

        }

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
    protected void stop() {

        frontLeft.stop();
        frontRight.stop();
        backLeft.stop();
        backRight.stop();

    }

    public void setDriverOffset(double driverOffset){

        this.driverOffset = driverOffset;

    }

}