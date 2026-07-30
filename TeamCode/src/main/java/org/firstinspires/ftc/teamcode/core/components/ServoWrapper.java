package org.firstinspires.ftc.teamcode.core.components;

import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.teamcode.core.templates.BaseOpMode;
import org.firstinspires.ftc.teamcode.core.templates.Component;
import org.firstinspires.ftc.teamcode.core.util.LogEntry;


public final class ServoWrapper extends Component {

    public enum ServoMode {STANDARD, CONTINUOUS}
    private final ServoImplEx servo;
    private final CRServoImplEx crServo;
    private final ServoMode servoMode;

    public ServoWrapper(BaseOpMode opMode, String componentName, ServoMode servoMode) {

        super(opMode, componentName);

        this.servoMode = servoMode;

        ServoImplEx tempServo;
        CRServoImplEx tempCRServo;

        switch(servoMode) {

            case STANDARD:
                try {

                    tempServo = opMode.hardwareMap.get(ServoImplEx.class, componentName);

                } catch (Exception e) {

                    opMode.logData(new LogEntry(BaseOpMode.EntryType.ERROR, "[ERROR] in " + ServoWrapper.class.getName() + ": failed to initialize component " + componentName , null));
                    opMode.requestOpModeStop();
                    tempServo = null;

                }

                servo = tempServo;
                crServo = null;
                break;

            case CONTINUOUS:
                try {

                    tempCRServo = opMode.hardwareMap.get(CRServoImplEx.class, componentName);

                } catch (Exception e) {

                    opMode.logData(new LogEntry(BaseOpMode.EntryType.ERROR, "[ERROR] in " + ServoWrapper.class.getName() + ": failed to initialize component " + componentName , null));
                    opMode.requestOpModeStop();
                    tempCRServo = null;

                }

                crServo = tempCRServo;
                servo = null;
                break;

            default:
                opMode.logData(new LogEntry(BaseOpMode.EntryType.ERROR, "[ERROR] in " + ServoWrapper.class.getName() + ": invalid servo mode for " + componentName , null));
                opMode.requestOpModeStop();
                servo = null;
                crServo = null;
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
