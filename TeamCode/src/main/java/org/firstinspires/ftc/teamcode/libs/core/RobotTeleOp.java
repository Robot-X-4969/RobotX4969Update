package org.firstinspires.ftc.teamcode.libs.core;

public abstract class RobotTeleOp extends BaseOpMode {

    protected RobotTeleOp() {

        super();

    }

    @Override
    protected void control_loop() {

        super.control_loop();

        for(Module module : modules) {

            module.control_loop();

        }

    }

}
