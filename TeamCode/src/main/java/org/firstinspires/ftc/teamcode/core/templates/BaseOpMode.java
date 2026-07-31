package org.firstinspires.ftc.teamcode.core.templates;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.core.util.DriverStation;
import org.firstinspires.ftc.teamcode.core.util.LogEntry;
import org.firstinspires.ftc.teamcode.core.util.Scheduler;

import java.util.ArrayList;

public abstract class BaseOpMode extends OpMode {

    public enum EntryType {ERROR, DEBUG};

    private final DriverStation driverStation;
    private final Scheduler scheduler;
    private final ArrayList<Module> modules;
    private final ArrayList<LogEntry> errorLog;
    private final ArrayList<LogEntry> debugLog;

    public BaseOpMode(){

        driverStation = new DriverStation(gamepad1, gamepad2);
        scheduler = new Scheduler(this);
        modules = new ArrayList<>();
        errorLog = new ArrayList<>();
        debugLog = new ArrayList<>();

    }

    @Override
    public void init() {

    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start(){

    }

    @Override
    public void loop() {

        driverStation.update();
        scheduler.pollEvents();


        sendData();
        telemetry.update();

    }

    @Override
    public void stop() {

        sendData();
        telemetry.update();

    }

    public void sendData(){

        for(LogEntry logEntry : debugLog){

            telemetry.addData(logEntry.getCaption(), logEntry.getMessage());

        }

        for(LogEntry logEntry : errorLog){

            telemetry.log().add(logEntry.getMessage());

        }

        debugLog.clear();
        errorLog.clear();

    }

    public void logData(LogEntry logEntry){

        switch(logEntry.getEntryType()){

            case ERROR:
                errorLog.add(logEntry);
                break;

            case DEBUG:
                debugLog.add(logEntry);
                break;

        }

    }

    public DriverStation getDriverStation(){

        return driverStation;

    }

    public Scheduler getScheduler() {

        return scheduler;

    }

}
