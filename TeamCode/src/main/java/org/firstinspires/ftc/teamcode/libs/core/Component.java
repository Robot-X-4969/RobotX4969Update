package org.firstinspires.ftc.teamcode.libs.core;

import org.firstinspires.ftc.teamcode.libs.util.LogEntry;

/**
 * Abstract base class representing a hardware component wrapper within the robot framework.
 * <p>
 * Handles standardizing hardware map retrieval for subclasses and provides automated
 * error logging and graceful OpMode termination if a requested hardware component fails to initialize.
 * </p>
 *
 * @author Gavin Farrell
 * @version 1.0
 */
public abstract class Component {

    /** The active OpMode instance providing access to hardware maps and logging. */
    protected final BaseOpMode opMode;

    /** The configured hardware map device name for this component. */
    protected final String componentName;

    /**
     * Constructs a new Component bound to an OpMode and hardware name.
     *
     * @param opMode        The active {@link BaseOpMode} instance.
     * @param componentName The name of the device as registered in the FTC Driver Station hardware map.
     */
    protected Component(BaseOpMode opMode, String componentName) {

        this.opMode = opMode;
        this.componentName = componentName;

    }

    /**
     * Safely retrieves a hardware device instance from the OpMode's hardware map.
     * <p>
     * If the hardware device cannot be found or fails to map, an error is logged to
     * the system logs and the active OpMode stop is requested.
     * </p>
     *
     * @param <T>       The class type of the hardware device being fetched.
     * @param classType The {@link Class} object corresponding to the hardware type.
     * @return The instantiated hardware device object, or {@code null} if initialization fails.
     */
    protected <T> T getHardware(Class<T> classType) {

        T hardwareComponent;

        try {

            hardwareComponent = opMode.hardwareMap.get(classType, componentName);

        } catch (Exception e) {

            opMode.logData(new LogEntry(LogEntry.EntryType.ERROR, " in " + classType.getName() + ": failed to initialize component " + componentName, null));
            opMode.requestOpModeStop();

            hardwareComponent = null;

        }

        return hardwareComponent;

    }

}