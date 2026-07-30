package org.firstinspires.ftc.teamcode.core.util;

import org.firstinspires.ftc.teamcode.core.templates.RobotOpMode;

import java.util.ArrayList;

public final class DriverInterface {

    private final ArrayList<MenuOption> menuOptions;
    private final RobotOpMode opMode;


    public DriverInterface(RobotOpMode opMode) {

        this.opMode = opMode;

        this.menuOptions = new ArrayList<>();

    }









}

class MenuOption {

    private final ArrayList<MenuOption> subOptions;
    private final String name;
    private final Runnable action;



}

class MenuBuilder {

    private final String name;
    private ;

    public MenuBuilder() {

        this.name = "Menu";
        this.

    }

    public static MenuBuilder create() {

        return new MenuBuilder();

    }

























}
