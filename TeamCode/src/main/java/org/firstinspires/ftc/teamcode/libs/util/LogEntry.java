package org.firstinspires.ftc.teamcode.libs.util;

/**
 * Represents an individual logging entry within the robot telemetry or diagnostic logging system.
 * <p>
 * Stores structural details about a single logged message, including its severity/type,
 * main body text, and an associated label or caption.
 * </p>
 *
 * @author Gavin Farrell
 * @version 1.0
 */
public final class LogEntry {
    /**
     * Categorizes log entries by severity or purpose for telemetry output routing.
     */
    public enum EntryType {

        /** Critical error conditions displayed in the persistent telemetry log stream. */
        ERROR,
        /** Real-time diagnostic metrics displayed as temporary telemetry data lines. */
        DEBUG,
        /** Non-critical warning alerts. */
        WARNING,
        /** Informational messages. */
        INFO

    }

    /** The classification or severity level (e.g., INFO, WARNING, ERROR) of this log entry. */
    private final EntryType entryType;

    /** The detailed text message recorded in this log entry. */
    private final String message;

    /** The descriptive title, label, or header associated with this log entry. */
    private final String caption;

    /**
     * Constructs a new LogEntry with a specified type, message, and caption.
     *
     * @param entryType The {@link EntryType} classifying the log level.
     * @param message   The core message content of the log.
     * @param caption   The label or title for the logged entry.
     */
    public LogEntry(EntryType entryType, String message, String caption) {

        this.entryType = entryType;
        this.message = message;
        this.caption = caption;

    }

    /**
     * Gets the entry type classification for this log item.
     *
     * @return The {@link EntryType} representing the logging severity level.
     */
    public EntryType getEntryType() {
        return this.entryType;
    }

    /**
     * Gets the main message content of this log entry.
     *
     * @return The log message text string.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Gets the caption or title assigned to this log entry.
     *
     * @return The caption string.
     */
    public String getCaption() {
        return this.caption;
    }

}