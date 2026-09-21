package org.firstinspires.ftc.teamcode.libs.util;

import org.firstinspires.ftc.teamcode.libs.core.BaseOpMode;

import java.util.ArrayList;

/**
 * Manages data logging and telemetry output for debugging and runtime history tracking.
 * <p>
 * Categorizes log entries into persistent history or debug logs, displaying them
 * to telemetry based on severity and active mode flags.
 * </p>
 *
 * @author Gavin Farrell
 * @version 1.0
 */
public final class Logger {

    /** The OpMode context used for checking debug flags and writing to telemetry. */
    private final BaseOpMode opMode;

    /** Stores persistent events such as errors, warnings, and informational entries. */
    private final ArrayList<LogEntry> historyLog;

    /** Stores non-critical debug messages intended for diagnostic output. */
    private final ArrayList<LogEntry> debugLog;

    /**
     * Constructs a {@code Logger} instance associated with a specific OpMode.
     *
     * @param opMode The active {@link BaseOpMode} instance.
     */
    public Logger(BaseOpMode opMode) {

        this.opMode = opMode;
        this.historyLog = new ArrayList<>();
        this.debugLog = new ArrayList<>();

    }

    /**
     * Routes a log entry into either the history log or the debug log based on its type.
     *
     * @param logEntry The {@link LogEntry} to record.
     */
    public void logData(LogEntry logEntry) {

        if(logEntry.getEntryType() == LogEntry.EntryType.ERROR || logEntry.getEntryType() == LogEntry.EntryType.WARNING || logEntry.getEntryType() == LogEntry.EntryType.INFO){

            historyLog.add(logEntry);

        } else {

            debugLog.add(logEntry);

        }

    }

    /**
     * Formats and pushes accumulated debug and history log entries to telemetry.
     */
    public void sendTelemetry(){

        if(opMode.isInDebugMode()){

            opMode.telemetry.addLine("");
            opMode.telemetry.addLine("============== DEBUG LOG ================");

            for (LogEntry logEntry : debugLog) {

                opMode.telemetry.addData("[" + logEntry.getEntryType() + "] " + logEntry.getCaption(), logEntry.getMessage());

            }

        }

        if(!historyLog.isEmpty()){

            opMode.telemetry.addLine("");
            opMode.telemetry.addLine("============== HISTORY LOG ==============");

            for (LogEntry logEntry : historyLog) {

                opMode.telemetry.addLine("[" + logEntry.getEntryType() + "] " + logEntry.getMessage());

            }

        }

        opMode.telemetry.update();

    }

}