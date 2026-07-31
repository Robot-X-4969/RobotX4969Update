package org.firstinspires.ftc.teamcode.core.templates;

import org.firstinspires.ftc.teamcode.core.util.Scheduler;

public abstract class Component {
    protected final BaseOpMode opMode;
    protected final String componentName;

    public Component(BaseOpMode opMode, String componentName) {

        this.opMode = opMode;
        this.componentName = componentName;

    }

}
