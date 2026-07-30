package org.firstinspires.ftc.teamcode.core.templates;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public abstract class Component {
    protected final BaseOpMode opMode;
    protected final String componentName;

    public Component(BaseOpMode opMode, String componentName) {

        this.opMode = opMode;
        this.componentName = componentName;

    }

}
