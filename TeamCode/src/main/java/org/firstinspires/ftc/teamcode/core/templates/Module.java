package org.firstinspires.ftc.teamcode.core.templates;

public abstract class Module {

    protected final BaseOpMode opMode;

    public Module(BaseOpMode opMode){

        this.opMode = opMode;

    }

    public abstract void init();

    public abstract void init_loop();

    public abstract void loop();

    public abstract void stop();

}
