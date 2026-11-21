package org.firstinspires.ftc.teamcode.libs.core;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.libs.util.DriverMenu;
import org.firstinspires.ftc.teamcode.libs.util.DriverStation;
import org.firstinspires.ftc.teamcode.libs.util.LogEntry;
import org.firstinspires.ftc.teamcode.libs.util.Logger;
import org.firstinspires.ftc.teamcode.libs.util.Scheduler;

import java.util.ArrayList;

public abstract class RobotTeleOp extends OpMode implements BaseOpMode {

    protected final DriverStation driverStation;
    protected final Scheduler scheduler;
    protected final Logger logger;
    protected final ArrayList<Module> modules;
    protected final DriverMenu driverMenu;
    protected boolean debugMode;

    protected RobotTeleOp() {

        driverStation = new DriverStation();
        scheduler = new Scheduler(this);
        logger = new Logger(this);

        modules = initModules();
        driverMenu = initDriverMenu();

        debugMode = false;

    }

    public abstract DriverMenu initDriverMenu();
    public abstract ArrayList<Module> initModules();

    protected void control_loop() {

        if(driverStation.getGamepad1().getRightStickButton().justPressed()){

            debugMode = !debugMode;

        }

    }

    @Override
    public void init() {

        try{

            driverStation.getGamepad1().bind(gamepad1);
            driverStation.getGamepad2().bind(gamepad2);

            for(Module module : modules){

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

            driverStation.update();
            scheduler.pollEvents();

            for(Module module : modules){

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

            driverStation.update();
            scheduler.pollEvents();

            for(Module module : modules){

                module.start();

            }

            logger.sendTelemetry();

        } catch (Exception e) {

            handleException(e);

        }


    }

    @Override
    public void loop() {

        try{
            driverStation.update();
            scheduler.pollEvents();

            control_loop();

            for(Module module : modules){

                module.control_loop();

            }

            for(Module module : modules){

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

            for(Module module : modules){

                module.stop();

            }

            logger.sendTelemetry();

        } catch (Exception e) {

            handleException(e);

        }

    }

    private void handleException(Exception e) {

        String msg = e.getMessage() != null ? e.getMessage().split("\n")[0] : e.toString();

        logger.logData(new LogEntry(LogEntry.EntryType.ERROR, msg,null));

        logger.sendTelemetry();

        try {

            Thread.sleep(10000);

        } catch (InterruptedException ie) {

            throw new RuntimeException(ie);

        }

        requestOpModeStop();

    }

    public final void logData(LogEntry logEntry) {logger.logData(logEntry);}

    public final Scheduler getScheduler() {return scheduler;}

    public final ArrayList<Module> getModules() {return modules;}

    public final boolean isInDebugMode() {return debugMode;}

    public final DriverStation getDriverStation() {return driverStation;}
    public final Telemetry getTelemetry() {return telemetry;}


}