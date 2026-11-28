package org.firstinspires.ftc.teamcode.libs.core;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.libs.util.Clock;
import org.firstinspires.ftc.teamcode.libs.util.DriverMenu;
import org.firstinspires.ftc.teamcode.libs.util.DriverStation;
import org.firstinspires.ftc.teamcode.libs.util.LogEntry;
import org.firstinspires.ftc.teamcode.libs.util.Logger;
import org.firstinspires.ftc.teamcode.libs.util.Scheduler;

import java.util.HashMap;

public abstract class RobotTeleOp extends OpMode implements BaseOpMode {

    protected final DriverStation driverStation;
    protected final Scheduler scheduler;
    protected final Logger logger;
    protected final HashMap<String, Module> modules;
    protected final DriverMenu driverMenu;
    protected final Clock stopwatch;
    protected double deltaTime;
    protected boolean debugMode;
    protected boolean errorMode;

    protected RobotTeleOp() {

        driverStation = new DriverStation();
        scheduler = new Scheduler(this);
        logger = new Logger(this);
        stopwatch = Clock.asStopwatch();
        deltaTime = 0;
        debugMode = false;

        modules = new HashMap<>();
        driverMenu = initDriverMenu();

    }

    public abstract DriverMenu initDriverMenu();
    public abstract void initModules();

    protected void control_loop() {

        if(driverStation.getGamepad1().getRightStickButton().justPressed()){

            debugMode = !debugMode;

        }

    }

    @Override
    public void init() {

        try{

            initModules();

            stopwatch.reset();
            stopwatch.start();

            driverStation.getGamepad1().bind(gamepad1);
            driverStation.getGamepad2().bind(gamepad2);

            for(Module module : modules.values()){

                module.init();

            }

            logger.sendTelemetry();

        } catch (Exception e) {

            handleException(e);

        }

    }

    @Override
    public void init_loop() {
        try{

            deltaTime = stopwatch.getElapsedSecondsTime();
            stopwatch.reset();
            stopwatch.start();

            driverStation.update();
            scheduler.pollEvents();

            for(Module module : modules.values()){

                module.init_loop();

            }

            if(driverMenu != null) {

                driverMenu.updateMenu();
                driverMenu.displayMenu();

            }

            logger.sendTelemetry();

        } catch (Exception e) {

            handleException(e);

        }

    }

    @Override
    public void start() {

        try {

            stopwatch.reset();
            stopwatch.start();

            driverStation.update();
            scheduler.pollEvents();

            for(Module module : modules.values()){

                module.start();

            }

            logger.sendTelemetry();

        } catch (Exception e) {

            handleException(e);

        }


    }

    @Override
    public void loop() {

        logData(new LogEntry(LogEntry.EntryType.DEBUG, " " + deltaTime, "Delta:"));
        logData(new LogEntry(LogEntry.EntryType.DEBUG, " " + driverStation.getGamepad1().getLeftStickY(), "Joystick:"));

        try{

            deltaTime = stopwatch.getElapsedSecondsTime();
            stopwatch.reset();
            stopwatch.start();

            driverStation.update();
            scheduler.pollEvents();

            control_loop();

            for(Module module : modules.values()){

                module.control_loop();

            }

            for(Module module : modules.values()){

                module.loop();

            }

            logger.sendTelemetry();

        } catch (Exception e) {

            handleException(e);

        }

    }

    @Override
    public void stop() {

        try{

            driverStation.update();
            scheduler.pollEvents();

            for(Module module : modules.values()){

                module.stop();

            }

            logger.sendTelemetry();

        } catch (Exception e) {

            handleException(e);

        }

    }

    private void handleException(Exception e) {

        errorMode = true;

        String msg = e.getMessage() != null ? e.getMessage().split("\n")[0] : e.toString();

        logger.logData(new LogEntry(LogEntry.EntryType.ERROR, msg,null));

        logger.sendTelemetry();

        try{

            Thread.sleep(4000);

        } catch (InterruptedException ie) {

            Thread.currentThread().interrupt();

        }

        requestOpModeStop();

    }


    public final void logData(LogEntry logEntry) {logger.logData(logEntry);}
    public final Scheduler getScheduler() {return scheduler;}
    public final HashMap<String, Module> getModules() {return modules;}
    public final boolean isInDebugMode() {return debugMode;}
    public final DriverStation getDriverStation() {return driverStation;}
    public final Telemetry getTelemetry() {return telemetry;}
    public final double getDeltaTime() {return deltaTime;}
    public final HardwareMap getHardwareMap() {return hardwareMap;}

}