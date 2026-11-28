package org.firstinspires.ftc.teamcode.libs.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.libs.core.BaseOpMode;
import org.firstinspires.ftc.teamcode.libs.core.Component;

public final class MotorWrapper extends Component {

    private final DcMotorEx motor;

    private final double ticksPerRev;

    public MotorWrapper(BaseOpMode opMode, String componentName, double ticksPerRev) {

        super(opMode, componentName);

        motor = getHardware(DcMotorEx.class);

        this.ticksPerRev = ticksPerRev;

    }

    public void setPower(double power) {

        motor.setPower(Math.max(-1.0, Math.min(1.0, power)));

    }

    public void setVelocity(double velocity) {

        if(motor.getMode() == DcMotor.RunMode.RUN_USING_ENCODER){

            motor.setVelocity(velocity);

        }

    }

    public void setPIDFCoefficients(double kF, double kP, double kI, double kD){

        if(motor.getMode() == DcMotor.RunMode.RUN_USING_ENCODER) {

            motor.setVelocityPIDFCoefficients(kP, kI, kD, kF);

        }

    }

    public void stop() {

        motor.setPower(0.0);

    }

    public void setReverse(boolean reverse) {

        motor.setDirection(reverse ? DcMotor.Direction.REVERSE : DcMotor.Direction.FORWARD);

    }

    public void setTimedRotation(double power, long millis) {

        setPower(power);

        opMode.getScheduler().scheduleEvent("stop" + componentName, millis, this::stop);

    }

    public void setMotorMode(DcMotor.RunMode mode) {

        motor.setMode(mode);

    }

    public double getRPM(){

        if(motor.getMode() == DcMotor.RunMode.RUN_USING_ENCODER){

            return motor.getVelocity() * 60 / ticksPerRev;

        }

        return 0.0;

    }

    public double getTicksPerRev(){

        return ticksPerRev;

    }

}
