package org.firstinspires.ftc.teamcode.core.core;

public abstract class Component {
    protected final BaseOpMode opMode;
    protected final String componentName;

    public Component(BaseOpMode opMode, String componentName) {

        this.opMode = opMode;
        this.componentName = componentName;

    }

}
