package org.firstinspires.ftc.teamcode.core.core;

public abstract class System {

    protected final BaseOpMode opMode;

    public System(BaseOpMode opMode){

        this.opMode = opMode;

    }

    public abstract void init();

    public abstract void init_loop();

    public abstract void loop();

    public abstract void stop();

    public void start() {
    }
}
