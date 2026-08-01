package org.firstinspires.ftc.teamcode.core.util;

import org.firstinspires.ftc.teamcode.core.core.BaseOpMode;

public final class LogEntry {

    private final BaseOpMode.EntryType entryType;
    private final String message;
    private final String caption;

    public LogEntry(BaseOpMode.EntryType entryType, String message, String caption) {

        this.entryType = entryType;
        this.message = message;
        this.caption = caption;

    }

    public BaseOpMode.EntryType getEntryType() {

        return this.entryType;

    }

    public String getMessage() {

        return this.message;

    }

    public String getCaption() {

        return this.caption;

    }

}
