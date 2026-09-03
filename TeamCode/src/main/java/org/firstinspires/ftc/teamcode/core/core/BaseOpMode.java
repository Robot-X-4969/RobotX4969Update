package org.firstinspires.ftc.teamcode.core.core;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.core.util.DriverStation;
import org.firstinspires.ftc.teamcode.core.util.LogEntry;
import org.firstinspires.ftc.teamcode.core.util.Logger;
import org.firstinspires.ftc.teamcode.core.util.Scheduler;

import java.util.ArrayList;

/**
 * Abstract base class extending the standard FTC {@link OpMode} to provide core framework utilities.
 * <p>
 * Integrated features include automated gamepad tracking via {@link DriverStation}, periodic event
 * scheduling via {@link Scheduler}, component/system management, and a categorized telemetry logging system.
 * </p>
 *
 * @author Gavin Farrell
 * @version 2.0
 */
public abstract class BaseOpMode extends OpMode {

    /** Manages gamepads and input state updates for drivers. */
    protected final DriverStation driverStation;

    /** Manages timed and repeating background events during OpMode execution. */
    protected final Scheduler scheduler;

    /** Manages categorized telemetry logging and output to the Driver Station display. */
    protected final Logger logger;

    /** List of robot subsystem instances registered to this OpMode. */
    protected final ArrayList<System> systems;

    /** Flag indicating whether debug mode is active (true) or inactive (false). */
    protected boolean debugMode = true;

    /**
     * Constructs a new BaseOpMode and initializes internal framework systems,
     * driver controls, schedulers, and log queues.
     */
    public BaseOpMode() {

        driverStation = new DriverStation();
        scheduler = new Scheduler(this);
        logger = new Logger(this);
        systems = new ArrayList<>();

    }

    /**
     * User-definable initialization step called once when the "INIT" button is pressed.
     * <p>
     * Override this method in child OpModes to set up hardware and initial state parameters.
     * </p>
     */
    @Override
    public void init() {

        driverStation.setGamepads(gamepad1, gamepad2);

        for(System system : systems){

            system.init();

        }

    }

    /**
     * Runs repeatedly after "INIT" is pressed until "START" is pressed.
     * <p>
     * Polls active events, updates driver input states, and refreshes telemetry output.
     * </p>
     */
    @Override
    public void init_loop() {

        scheduler.pollEvents();
        driverStation.update();

        for(System system : systems){

            system.init_loop();

        }

    }

    /**
     * User-definable execution step called once when the "START" button is pressed.
     * <p>
     * Override this method in child OpModes for setup routines right as autonomous or teleop begins.
     * </p>
     */
    @Override
    public void start() {

        for(System system : systems){

            system.start();

        }

    }

    /**
     * Runs repeatedly after "START" is pressed until "STOP" is pressed.
     * <p>
     * Polls inputs, executes scheduled events, flushes queued telemetry logs, and updates the display.
     * </p>
     */
    @Override
    public void loop() {

        telemetry.clearAll();

        scheduler.pollEvents();
        driverStation.update();

        if(driverStation.getGamepad1().getRightStickButton().justPressed()){

            debugMode = !debugMode;

        }

        for(System system : systems){

            system.loop();

        }

        logger.sendTelemetry();

    }

    /**
     * Runs once when the OpMode is stopped or times out.
     * <p>
     * Flushes remaining queued telemetry data to the Driver Station screen before teardown.
     * </p>
     */
    @Override
    public void stop() {

        for(System system : systems){

            system.stop();

        }

        logger.sendTelemetry();

    }
    
    /**
     * Queues a new log entry for rendering on the next telemetry frame update based on its entry type.
     *
     * @param logEntry The {@link LogEntry} object to be added to the output queue.
     */
    public final void logData(LogEntry logEntry) {logger.logData(logEntry);}

    /**
     * Gets the driver station manager instance associated with this OpMode.
     *
     * @return The active {@link DriverStation} instance.
     */
    public final DriverStation getDriverStation() {
        return driverStation;
    }

    /**
     * Gets the event scheduler manager instance associated with this OpMode.
     *
     * @return The active {@link Scheduler} instance.
     */
    public final Scheduler getScheduler() {
        return scheduler;
    }

    /**
     * Gets the telemetry logger manager instance associated with this OpMode.
     *
     * @return The active {@link Logger} instance.
     */
    public final Logger getLogger() {return logger;}

    /**
     * Gets the list of registered robot subsystem instances for this OpMode.
     *
     * @return The list of registered {@link System} instances for this OpMode.
     */
    public final ArrayList<System> getSystems() {return systems;}

    /**
     * Checks whether debug mode is currently active for this OpMode.
     * <p>
     * Debug mode can be toggled on or off during runtime by pressing the right stick button on gamepad 1.
     * </p>
     *
     * @return True if debug mode is active; false otherwise.
     */
    public final boolean isInDebugMode() {return debugMode;}

}