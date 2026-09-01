package org.firstinspires.ftc.teamcode.core.util;

import org.firstinspires.ftc.teamcode.core.core.BaseOpMode;

import java.util.ArrayList;

public final class MenuOption {

    private final BaseOpMode opMode;
    private final String name;
    private final Runnable action;
    private final ArrayList<MenuOption> subOptions;

    public MenuOption(BaseOpMode opMode, String name, Runnable action) {

        this.opMode = opMode;
        this.name = name;
        this.action = action;
        this.subOptions = null;

    }

    public MenuOption(BaseOpMode opMode, String name) {

        this.opMode = opMode;
        this.name = name;
        this.action = null;
        this.subOptions = new ArrayList<>();

    }

    public String getName() {

        return name;

    }

    public void runAction() {

        if(action == null) {

            opMode.logData(new LogEntry(BaseOpMode.EntryType.ERROR, "[ERROR] in " + MenuOption.class.getName() + ": action cannot be run because it is null", null));

            return;

        }

        action.run();

    }

    public ArrayList<MenuOption> getSubOptions() {

        if(subOptions == null) {

            opMode.logData(new LogEntry(BaseOpMode.EntryType.ERROR, "[ERROR] in " + MenuOption.class.getName() + ": subOptions cannot be returned because it is null", null));

            return new ArrayList<>();

        }

        return subOptions;

    }

    public boolean hasSubOptions() {

        return subOptions != null;

    }

}
