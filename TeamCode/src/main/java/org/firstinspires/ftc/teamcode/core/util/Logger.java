package org.firstinspires.ftc.teamcode.core.util;

import org.firstinspires.ftc.teamcode.core.core.BaseOpMode;

import java.util.ArrayDeque;
import java.util.Queue;

public final class Logger {
    private final BaseOpMode opMode;
    private final Queue<LogEntry> historyLog;
    private final Queue<LogEntry> debugLog;

    public Logger(BaseOpMode opMode) {
        this.opMode = opMode;
        this.historyLog = new ArrayDeque<>();
        this.debugLog = new ArrayDeque<>();
    }

    public void logData(LogEntry logEntry) {
        if(logEntry.getEntryType() == LogEntry.EntryType.ERROR || logEntry.getEntryType() == LogEntry.EntryType.WARNING){

            historyLog.add(logEntry);

        } else {

            debugLog.add(logEntry);

        }
    }

    public void sendTelemetry(){

        if(opMode.isInDebugMode()){

            for (LogEntry logEntry : debugLog) {

                debugLog.poll();

                opMode.telemetry.addData("[" + logEntry.getEntryType() + "]" + logEntry.getCaption(), logEntry.getMessage());

            }

        }

        for (LogEntry logEntry : historyLog) {

            historyLog.poll();

            opMode.telemetry.log().add("[" + logEntry.getEntryType() + "]" + logEntry.getMessage());

        }

        opMode.telemetry.update();

    }
}
