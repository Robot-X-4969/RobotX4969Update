package org.firstinspires.ftc.teamcode.libs.components;

import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.teamcode.libs.core.BaseOpMode;
import org.firstinspires.ftc.teamcode.libs.core.Component;
import org.firstinspires.ftc.teamcode.libs.util.LogEntry;

/**
 * A wrapper component for controlling standard and continuous-rotation (CR) servos in FTC OpModes.
 * <p>
 * Provides position control for standard positional servos and power/stop controls
 * for continuous rotation servos while enforcing mode checking to prevent invalid hardware operations.
 * </p>
 *
 * @author John Daniher
 * @author Gavin Farrell (update)
 * @version 2.0
 */
public final class ServoWrapper extends Component {

    /**
     * Defines the operational mode of the servo hardware.
     */
    public enum ServoMode {
        /** Positional servo with angular control (0.0 to 1.0). */
        STANDARD,
        /** Continuous rotation servo with directional power control (-1.0 to 1.0). */
        CONTINUOUS
    }

    /** The standard positional servo hardware interface (null if mode is CONTINUOUS). */
    private final ServoImplEx servo;

    /** The continuous rotation servo hardware interface (null if mode is STANDARD). */
    private final CRServoImplEx crServo;

    /** The operational mode assigned to this servo instance. */
    private final ServoMode servoMode;

    /**
     * Constructs a new ServoWrapper component and fetches hardware interfaces from the OpMode map.
     *
     * @param opMode        The active {@link BaseOpMode} instance.
     * @param componentName The hardware map name of the servo.
     * @param servoMode     The operational mode (STANDARD or CONTINUOUS) for this servo.
     */
    public ServoWrapper(BaseOpMode opMode, String componentName, ServoMode servoMode) {
        super(opMode, componentName);

        this.servoMode = servoMode;

        switch (servoMode) {
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
                opMode.logData(new LogEntry(LogEntry.EntryType.ERROR, "[ERROR] in " + ServoWrapper.class.getName() + ": invalid servo mode for " + componentName, null));
                opMode.requestOpModeStop();
                break;
        }
    }

    /**
     * Sets the target position of a standard positional servo.
     * <p>
     * Logs an error if invoked on a continuous rotation servo.
     * </p>
     *
     * @param position Target position normalized between 0.0 and 1.0.
     */
    public void setServoPosition(double position) {
        if (servoMode == ServoMode.STANDARD) {
            servo.setPosition(position);
        } else {
            opMode.logData(new LogEntry(LogEntry.EntryType.ERROR, "[ERROR] in " + ServoWrapper.class.getName() + ": attempted to set the position of the continuous rotation servo " + componentName, null));
        }
    }

    /**
     * Gets the last written target position of a standard positional servo.
     * <p>
     * Logs an error and returns 0.0 if invoked on a continuous rotation servo.
     * </p>
     *
     * @return Target position normalized between 0.0 and 1.0, or 0.0 on error.
     */
    public double getServoPosition() {
        if (servoMode == ServoMode.STANDARD) {
            return servo.getPosition();
        } else {
            opMode.logData(new LogEntry(LogEntry.EntryType.ERROR, "[ERROR] in " + ServoWrapper.class.getName() + ": attempted to get the position of the continuous rotation servo " + componentName, null));
        }

        return 0.0;
    }

    /**
     * Sets the rotation speed and direction of a continuous rotation servo.
     * <p>
     * Logs an error if invoked on a standard positional servo.
     * </p>
     *
     * @param power Target power level from -1.0 (full reverse) to 1.0 (full forward).
     */
    public void setServoPower(double power) {
        if (servoMode == ServoMode.CONTINUOUS) {
            crServo.setPower(power);
        } else {
            opMode.logData(new LogEntry(LogEntry.EntryType.ERROR, "[ERROR] in " + ServoWrapper.class.getName() + ": attempted to set the power of the standard servo " + componentName, null));
        }
    }

    /**
     * Stops a continuous rotation servo by setting its power to 0.0.
     * <p>
     * Logs an error if invoked on a standard positional servo.
     * </p>
     */
    public void stopServo() {
        if (servoMode == ServoMode.CONTINUOUS) {
            crServo.setPower(0.0);
        } else {
            opMode.logData(new LogEntry(LogEntry.EntryType.ERROR, "[ERROR] in " + ServoWrapper.class.getName() + ": attempted to stop the standard servo " + componentName, null));
        }
    }

    /**
     * Reverses or restores default motor/servo direction for either standard or continuous rotation modes.
     *
     * @param reversed {@code true} to reverse hardware rotation direction, {@code false} for standard forward direction.
     */
    public void setReversed(boolean reversed) {
        if (servoMode == ServoMode.STANDARD) {
            servo.setDirection(reversed ? ServoImplEx.Direction.REVERSE : ServoImplEx.Direction.FORWARD);
        } else {
            crServo.setDirection(reversed ? CRServoImplEx.Direction.REVERSE : CRServoImplEx.Direction.FORWARD);
        }
    }
}