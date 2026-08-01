package org.firstinspires.ftc.teamcode.core.util;

import org.firstinspires.ftc.teamcode.core.core.BaseOpMode;

public final class DriverInterface {
    private final BaseOpMode opMode;
    private final MenuBuilder menu;

    public DriverInterface(BaseOpMode opMode, MenuBuilder menu) {

        this.opMode = opMode;
        this.menu = menu;

    }

    public static MenuBuilder buildMenu(BaseOpMode opMode) {

        return new MenuBuilder("root", null, opMode);

    }

}


























