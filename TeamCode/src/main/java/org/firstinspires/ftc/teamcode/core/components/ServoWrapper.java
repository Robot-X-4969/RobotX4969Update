package org.firstinspires.ftc.teamcode.core.components;

import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.teamcode.core.core.BaseOpMode;
import org.firstinspires.ftc.teamcode.core.core.Component;
import org.firstinspires.ftc.teamcode.core.util.LogEntry;

public final class ServoWrapper extends Component {

    public enum ServoMode {STANDARD, CONTINUOUS}
    private final ServoImplEx servo;
    private final CRServoImplEx crServo;
    private final ServoMode servoMode;

    public ServoWrapper(BaseOpMode opMode, String componentName, ServoMode servoMode) {

        super(opMode, componentName);

        this.servoMode = servoMode;

        switch(servoMode) {

            case STANDARD:

                servo = getHardware(ServoImplEx.class);
                crServo = null;
                break;

            case CONTINUOUS:

                crServo = getHardware(CRServoImplEx.class);
                servo = null;
                break;

            default:

                servo = null;
                crServo = null;
                opMode.logData(new LogEntry(BaseOpMode.EntryType.ERROR, "[ERROR] in " + ServoWrapper.class.getName() + ": invalid servo mode for " + componentName , null));
                opMode.requestOpModeStop();
                break;

        }

    }

    public void setServoPosition(double position) {

        if(servoMode == ServoMode.STANDARD) {

            servo.setPosition(position);

        } else {

            opMode.logData(new LogEntry(BaseOpMode.EntryType.ERROR, "[ERROR] in " + ServoWrapper.class.getName() + ": attempted to set the position of the continuous rotation servo " + componentName , null));

        }

    }

    public double getServoPosition() {

        if(servoMode == ServoMode.STANDARD) {

            return servo.getPosition();

        } else {

            opMode.logData(new LogEntry(BaseOpMode.EntryType.ERROR, "[ERROR] in " + ServoWrapper.class.getName() + ": attempted to get the position of the continuous rotation servo " + componentName , null));

        }

        return 0.0;

    }


    public void setServoPower(double power) {

        if(servoMode == ServoMode.CONTINUOUS) {

            crServo.setPower(power);

        } else {

            opMode.logData(new LogEntry(BaseOpMode.EntryType.ERROR, "[ERROR] in " + ServoWrapper.class.getName() + ": attempted to set the power of the standard servo " + componentName , null));

        }

    }

    public void stopServo() {

        if(servoMode == ServoMode.CONTINUOUS) {

            crServo.setPower(0.0);

        } else {

            opMode.logData(new LogEntry(BaseOpMode.EntryType.ERROR, "[ERROR] in " + ServoWrapper.class.getName() + ": attempted to stop the standard servo " + componentName , null));

        }

    }

    public void setReversed(boolean reversed){

        if(servoMode == ServoMode.STANDARD) {

            servo.setDirection(reversed ? ServoImplEx.Direction.REVERSE : ServoImplEx.Direction.FORWARD);

        } else {

            crServo.setDirection(reversed ? CRServoImplEx.Direction.REVERSE : CRServoImplEx.Direction.FORWARD);

        }

    }

}
