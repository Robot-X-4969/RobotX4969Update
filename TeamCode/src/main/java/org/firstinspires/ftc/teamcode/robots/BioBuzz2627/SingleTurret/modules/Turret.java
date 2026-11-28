package org.firstinspires.ftc.teamcode.robots.BioBuzz2627.SingleTurret.modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.libs.components.Limelight;
import org.firstinspires.ftc.teamcode.libs.components.MotorWrapper;
import org.firstinspires.ftc.teamcode.libs.components.Pinpoint;
import org.firstinspires.ftc.teamcode.libs.components.ServoWrapper;
import org.firstinspires.ftc.teamcode.libs.core.BaseOpMode;
import org.firstinspires.ftc.teamcode.libs.core.Module;

public class Turret extends Module {

    private final ServoWrapper turretServo;
    private final MotorWrapper flywheelMotor1;
    private final MotorWrapper flywheelMotor2;
    private final Limelight limelight;
    private final Pinpoint pinpoint;
    private Pose2D robotPose;

    private Pose2D currentTarget;

    private double lastPosX;
    private double lastPosZ;

    public Turret(BaseOpMode opMode, Pinpoint pinpoint) {

        super(opMode);

        turretServo = new ServoWrapper(opMode, "turretServo", ServoWrapper.ServoMode.STANDARD);

        flywheelMotor1 = new MotorWrapper(opMode, "flywheelMotor1", 28.0);
        flywheelMotor2 = new MotorWrapper(opMode, "flywheelMotor2", 28.0);

        limelight = new Limelight(opMode, "limelight");

        this.pinpoint = pinpoint;

    }

    @Override
    protected void init() {

        flywheelMotor1.setMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheelMotor2.setMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);

        turretServo.setServoPosition(0.5);

        lastPosX = robotPose.getX(DistanceUnit.CM);
        lastPosZ = robotPose.getY(DistanceUnit.CM);

    }

    @Override
    protected void init_loop() {

    }

    @Override
    protected void loop() {

        double diffX = pinpoint.getX(DistanceUnit.CM) - currentTarget.getX(DistanceUnit.CM);
        double diffZ = pinpoint.getY(DistanceUnit.CM) - currentTarget.getY(DistanceUnit.CM);

        double distance = Math.sqrt(diffX * diffX + diffZ * diffZ);
        double height = 0;
        double entryAngle = 0;
        double g = 981.0;

        double staticLaunchAngle = Math.atan(((2 * height) / distance) - Math.tan(entryAngle));
        double staticVelocity = (distance / Math.cos(staticLaunchAngle)) * Math.sqrt(g / (2 * (distance * Math.tan(staticLaunchAngle) - height)));

        double velocityS = staticVelocity * Math.cos(staticLaunchAngle);
        double velocityY = staticVelocity * Math.sin(staticLaunchAngle);

        double staticTurretAngle = Math.atan2(diffZ, diffX);

        double velocityX = -velocityS * Math.cos(staticTurretAngle);
        double velocityZ = -velocityS * Math.sin(staticTurretAngle);

        double robotVelocityX = (pinpoint.getX(DistanceUnit.CM) - lastPosX) / opMode.getDeltaTime();
        double robotVelocityZ = (pinpoint.getY(DistanceUnit.CM) - lastPosZ) / opMode.getDeltaTime();

        double adjustedVelocityX = velocityX - robotVelocityX;
        double adjustedVelocityZ = velocityZ - robotVelocityZ;
        double adjustedVelocityS = Math.sqrt(adjustedVelocityX * adjustedVelocityX + adjustedVelocityZ * adjustedVelocityZ);

        double adjustedTurretAngle = Math.atan2(-adjustedVelocityZ, -adjustedVelocityX);

        double adjustedLaunchAngle = Math.atan2(velocityY, adjustedVelocityS);
        double adjustedTotalVelocity = Math.sqrt(adjustedVelocityS * adjustedVelocityS + velocityY * velocityY);

        double servoPosition = (0.5 + ((adjustedTurretAngle - pinpoint.getHeading(AngleUnit.RADIANS)) / (2.0 * Math.PI)));

        if(servoPosition > 1.0){

            servoPosition -= 1.0;

        } else if (servoPosition < 0.0){

            servoPosition += 1.0;

        }

        turretServo.setServoPosition(servoPosition);

        lastPosX = pinpoint.getX(DistanceUnit.CM);
        lastPosZ = pinpoint.getY(DistanceUnit.CM);

        double diameter = 1.0;

        double shooterTicksPerSecond = ((adjustedTotalVelocity / diameter) / Math.PI) * flywheelMotor1.getTicksPerRev();

        flywheelMotor1.setVelocity(shooterTicksPerSecond);
        flywheelMotor2.setVelocity(shooterTicksPerSecond);

    }

    @Override
    protected void control_loop() {


    }

    @Override
    protected void start() {

    }

    @Override
    protected void stop() {

    }

    public void setStartingRobotPose(Pose2D robotPose) {

        pinpoint.setStartingPose(robotPose);

    }

}
