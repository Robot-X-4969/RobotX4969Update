package org.firstinspires.ftc.teamcode.libs.core;

public abstract class Module {

    protected final BaseOpMode opMode;

    protected Module(BaseOpMode opMode){

        this.opMode = opMode;

    }

    protected abstract void init_loop();
    protected abstract void loop();
    protected abstract void control_loop();
    protected abstract void init();

    protected abstract void start();
    protected abstract void stop();

}
