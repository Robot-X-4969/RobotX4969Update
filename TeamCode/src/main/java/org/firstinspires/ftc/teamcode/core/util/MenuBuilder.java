package org.firstinspires.ftc.teamcode.core.util;

import org.firstinspires.ftc.teamcode.core.core.BaseOpMode;

import java.util.ArrayList;

public final class MenuBuilder {

    private final MenuBuilder parent;
    private final String optionName;
    private final Runnable action;
    private final ArrayList<MenuBuilder> subOptions;
    private final BaseOpMode opMode;

    private final boolean isSubMenu;

    public MenuBuilder(String optionName, Runnable action, MenuBuilder parent, BaseOpMode opMode) {

        this.optionName = optionName;
        this.action = action;
        this.parent = parent;
        this.opMode = opMode;

        subOptions = new ArrayList<>();
        isSubMenu = false;

    }

    public MenuBuilder(String optionName, MenuBuilder parent, BaseOpMode opMode) {

        this.optionName = optionName;
        this.action = null;
        this.parent = parent;
        this.opMode = opMode;

        subOptions = new ArrayList<>();
        isSubMenu = true;

    }

    public MenuBuilder addMenuOption(String optionName, Runnable action) {

        MenuBuilder option = new MenuBuilder(optionName, action, this, opMode);

        if(!isSubMenu) {

            opMode.logData(new LogEntry(BaseOpMode.EntryType.ERROR, "[ERROR] in " + MenuBuilder.class.getName() + ": cannot add menu option to a non-submenu", null));
            opMode.requestOpModeStop();

        }

        subOptions.add(option);

        return this;

    }

    public MenuBuilder addSubMenu(String optionName) {

        MenuBuilder subMenu = new MenuBuilder(optionName, this, opMode);

        if(!isSubMenu) {

            opMode.logData(new LogEntry(BaseOpMode.EntryType.ERROR, "[ERROR] in " + MenuBuilder.class.getName() + ": cannot add submenu to a non-submenu", null));
            opMode.requestOpModeStop();

        }

        subOptions.add(subMenu);

        return subMenu;

    }

    public MenuBuilder endSubMenu() {

        return parent;

    }

    public DriverInterface bindMenu() {

        if(parent != null) {

            opMode.logData(new LogEntry(BaseOpMode.EntryType.ERROR, "[ERROR] in " + MenuBuilder.class.getName() + ": cannot bind to any menu other than root", null));
            opMode.requestOpModeStop();

        }

        return new DriverInterface(opMode, this);

    }

    public String getOptionName() {

        return optionName;

    }

    public Runnable getAction() {

        return action;

    }

    public ArrayList<MenuBuilder> getSubOptions() {

        return subOptions;

    }

}




