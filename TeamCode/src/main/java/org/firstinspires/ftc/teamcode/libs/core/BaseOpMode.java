package org.firstinspires.ftc.teamcode.libs.core;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.libs.util.DriverMenu;
import org.firstinspires.ftc.teamcode.libs.util.DriverStation;
import org.firstinspires.ftc.teamcode.libs.util.LogEntry;
import org.firstinspires.ftc.teamcode.libs.util.Logger;
import org.firstinspires.ftc.teamcode.libs.util.Scheduler;

import java.util.ArrayList;

public abstract class BaseOpMode extends OpMode {

    protected final DriverStation driverStation;
    protected final Scheduler scheduler;
    protected final Logger logger;
    protected final DriverMenu driverMenu;
    protected final ArrayList<Module> modules;
    protected boolean debugMode;

    protected BaseOpMode() {

        driverStation = new DriverStation();
        scheduler = new Scheduler(this);
        logger = new Logger(this);
        driverMenu = initDriverMenu();
        modules = new ArrayList<>();


        debugMode = false;

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

        initModules();

        driverStation.getGamepad1().bind(gamepad1);
        driverStation.getGamepad2().bind(gamepad2);

        for(Module module : modules){

            module.init();

        }

        logger.sendTelemetry();

    }

    @Override
    public void init_loop() {

        driverStation.update();
        scheduler.pollEvents();

        control_loop();

        for(Module module : modules){

            module.init_loop();

        }

        if(driverMenu != null) {

            driverMenu.updateMenu();
            driverMenu.displayMenu();

        }

        logger.sendTelemetry();

    }

    @Override
    public void start() {

        driverStation.update();
        scheduler.pollEvents();

        for(Module module : modules){

            module.start();

        }

        logger.sendTelemetry();

    }

    @Override
    public void loop() {

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

    }

    @Override
    public void stop() {

        driverStation.update();
        scheduler.pollEvents();

        for(Module module : modules){

            module.stop();

        }

        logger.sendTelemetry();

    }

    public final void logData(LogEntry logEntry) {logger.logData(logEntry);}

    public final DriverStation getDriverStation() {return driverStation;}

    public final Scheduler getScheduler() {return scheduler;}

    public final ArrayList<Module> getModules() {return modules;}

    public final boolean isInDebugMode() {return debugMode;}

}