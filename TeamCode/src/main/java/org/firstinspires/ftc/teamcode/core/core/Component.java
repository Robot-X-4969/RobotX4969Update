package org.firstinspires.ftc.teamcode.core.core;

import org.firstinspires.ftc.teamcode.core.util.LogEntry;

public abstract class Component {

    protected final BaseOpMode opMode;
    protected final String componentName;

    public Component(BaseOpMode opMode, String componentName) {

        this.opMode = opMode;
        this.componentName = componentName;

    }

    protected <T> T getHardware(Class<T> classType) {

        T hardwareComponent;

        try {

            hardwareComponent = opMode.hardwareMap.get(classType, componentName);

        } catch (Exception e) {

            opMode.logData(new LogEntry(BaseOpMode.EntryType.ERROR, "[ERROR] in " + classType.getName() + ": failed to initialize component " + componentName , null));
            opMode.requestOpModeStop();

            hardwareComponent = null;

        }

        return hardwareComponent;

    }

}
