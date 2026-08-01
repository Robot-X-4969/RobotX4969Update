package org.firstinspires.ftc.teamcode.core.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.core.core.BaseOpMode;
import org.firstinspires.ftc.teamcode.core.core.Component;
import org.firstinspires.ftc.teamcode.core.util.LogEntry;


public final class MotorWrapper extends Component {

    private final DcMotorEx motor;

    public MotorWrapper(BaseOpMode opMode, String componentName) {

        super(opMode, componentName);

        DcMotorEx tempMotor;

        try {

            tempMotor = opMode.hardwareMap.get(DcMotorEx.class, componentName);

        } catch (Exception e) {

            opMode.logData(new LogEntry(BaseOpMode.EntryType.ERROR, "[ERROR] in " + MotorWrapper.class.getName() + ": failed to initialize component " + componentName , null));
            opMode.requestOpModeStop();
            tempMotor = null;

        }

        motor = tempMotor;

    }

    public void setPower(double power) {

        motor.setPower(Math.max(-1.0, Math.min(1.0, power)));

    }

    public void stop() {

        motor.setPower(0.0);

    }

    public void setReverse(boolean reverse) {

        motor.setDirection (reverse ? DcMotor.Direction.REVERSE : DcMotor.Direction.FORWARD);

    }

    public void setTimedRotation(double power, long millis) {

        setPower(power);

        opMode.getScheduler().scheduleEvent("stop" + componentName, millis, this::stop);

    }

    public void setMotorMode(DcMotor.RunMode mode) {

        motor.setMode(mode);

    }

}
