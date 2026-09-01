package org.firstinspires.ftc.teamcode.core.util;

import org.firstinspires.ftc.teamcode.core.core.BaseOpMode;

import java.util.ArrayList;

public final class MenuBuilder {

    private final BaseOpMode opMode;
    private final MenuBuilder parent;
    private final String optionName;
    private final Runnable action;
    private final ArrayList<MenuBuilder> builders;

    public MenuBuilder(BaseOpMode opMode, String optionName, Runnable action, MenuBuilder parent) {

        this.opMode = opMode;
        this.parent = parent;
        this.optionName = optionName;
        this.action = action;
        this.builders = null;

    }

    public MenuBuilder(BaseOpMode opMode, String optionName, MenuBuilder parent) {

        this.opMode = opMode;
        this.parent = parent;
        this.optionName = optionName;
        this.action = null;
        this.builders = new ArrayList<>();

    }

    public static MenuBuilder createMenu(BaseOpMode opMode){

        return new MenuBuilder(opMode, "Root Menu", null);

    }

    public MenuBuilder addAction(String optionName, Runnable action) {

        if (isSubMenu()) {

            MenuBuilder menuAction = new MenuBuilder(opMode, optionName, action, this);
            builders.add(menuAction);

            return this;

        } else {

            opMode.logData(new LogEntry(BaseOpMode.EntryType.ERROR, "[ERROR] in " + MenuBuilder.class.getName() + ": cannot add menu option to a non-submenu", null));
            opMode.requestOpModeStop();

        }

        return this;

    }

    public MenuBuilder addSubMenu(String optionName) {

        if (isSubMenu()) {

            MenuBuilder subMenu = new MenuBuilder(opMode, optionName, this);
            builders.add(subMenu);
            return subMenu;

        } else {

            opMode.logData(new LogEntry(BaseOpMode.EntryType.ERROR, "[ERROR] in " + MenuBuilder.class.getName() + ": cannot add submenu to a non-submenu", null));
            opMode.requestOpModeStop();

        }

        return this;

    }

    public MenuBuilder endSubMenu() {

        if(isSubMenu() && parent != null) {

            return parent;

        }

        if(parent == null) {

            opMode.logData(new LogEntry(BaseOpMode.EntryType.ERROR, "[ERROR] in " + MenuBuilder.class.getName() + ": cannot end submenu on root menu", null));
            opMode.requestOpModeStop();

        } else {

            opMode.logData(new LogEntry(BaseOpMode.EntryType.ERROR, "[ERROR] in " + MenuBuilder.class.getName() + ": cannot end submenu on a non-submenu", null));
            opMode.requestOpModeStop();

        }

        return this;

    }

    public DriverMenu buildMenu(){

        if(parent == null) {

            return new DriverMenu(opMode, this.compile());

        } else {

            opMode.logData(new LogEntry(BaseOpMode.EntryType.ERROR, "[ERROR] in " + MenuBuilder.class.getName() + ": cannot build menu on a non-root menu", null));
            opMode.requestOpModeStop();

        }

        return new DriverMenu(opMode, new ArrayList<>());

    }

    public ArrayList<MenuOption> compile(){

        ArrayList<MenuOption> compiledOptions = new ArrayList<>();

        if(builders == null) {

            opMode.logData(new LogEntry(BaseOpMode.EntryType.ERROR, "[ERROR] in " + MenuBuilder.class.getName() + ": cannot compile a non-submenu", null));
            opMode.requestOpModeStop();

            return compiledOptions;

        }

        for(MenuBuilder builder : this.builders){

            if(builder.isSubMenu()){

                MenuOption subMenuOption = new MenuOption(builder.opMode, builder.optionName);

                ArrayList<MenuOption> subMenuOptions = builder.compile();

                subMenuOption.getSubOptions().addAll(subMenuOptions);

                compiledOptions.add(subMenuOption);

            } else {

                compiledOptions.add(new MenuOption(builder.opMode, builder.optionName, builder.action));

            }

        }

        return compiledOptions;

    }

    public boolean isSubMenu() {

        return builders != null;

    }

}