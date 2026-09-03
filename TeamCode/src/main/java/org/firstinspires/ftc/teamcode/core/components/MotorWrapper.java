package org.firstinspires.ftc.teamcode.core.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.core.core.BaseOpMode;
import org.firstinspires.ftc.teamcode.core.core.Component;

/**
 * A wrapper component for controlling DC motors in FTC OpModes.
 * <p>
 * Simplifies hardware access by providing bounded power control, direction reversal,
 * run mode configuration, and timed rotation scheduling.
 * </p>
 *
 * @author John Daniher
 * @author Gavin Farrell (update)
 * @version 2.0
 */
public final class MotorWrapper extends Component {

    /** The underlying {@link DcMotorEx} hardware instance. */
    private final DcMotorEx motor;

    /**
     * Constructs a new MotorWrapper component and retrieves the hardware motor interface.
     *
     * @param opMode        The active {@link BaseOpMode} instance.
     * @param componentName The hardware map name of the DC motor.
     */
    public MotorWrapper(BaseOpMode opMode, String componentName) {
        super(opMode, componentName);

        motor = getHardware(DcMotorEx.class);
    }

    /**
     * Sets the power level for the motor, clamping the input within the valid range of -1.0 to 1.0.
     *
     * @param power The desired power level (-1.0 to 1.0).
     */
    public void setPower(double power) {
        motor.setPower(Math.max(-1.0, Math.min(1.0, power)));
    }

    /**
     * Stops the motor by setting its power level to 0.0.
     */
    public void stop() {
        motor.setPower(0.0);
    }

    /**
     * Sets the motor's operational direction.
     *
     * @param reverse {@code true} to reverse motor rotation, {@code false} for standard forward direction.
     */
    public void setReverse(boolean reverse) {
        motor.setDirection(reverse ? DcMotor.Direction.REVERSE : DcMotor.Direction.FORWARD);
    }

    /**
     * Runs the motor at a specified power level for a fixed duration using the OpMode scheduler.
     * <p>
     * Automatically schedules a stop command via the OpMode's {@link org.firstinspires.ftc.teamcode.core.util.Scheduler}
     * once the specified duration in milliseconds has elapsed.
     * </p>
     *
     * @param power  The power level to apply during the timed rotation.
     * @param millis The duration in milliseconds to run the motor before stopping.
     */
    public void setTimedRotation(double power, long millis) {
        setPower(power);

        opMode.getScheduler().scheduleEvent("stop" + componentName, millis, this::stop);
    }

    /**
     * Sets the operational run mode for the motor (e.g., RUN_WITHOUT_ENCODER, RUN_USING_ENCODER, STOP_AND_RESET_ENCODER).
     *
     * @param mode The desired {@link DcMotor.RunMode} configuration.
     */
    public void setMotorMode(DcMotor.RunMode mode) {
        motor.setMode(mode);
    }
}