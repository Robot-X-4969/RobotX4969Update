package org.firstinspires.ftc.teamcode.robots.starterbot2627.modules;

import org.firstinspires.ftc.teamcode.libs.components.MotorWrapper;
import org.firstinspires.ftc.teamcode.libs.components.ServoWrapper;
import org.firstinspires.ftc.teamcode.libs.core.BaseOpMode;
import org.firstinspires.ftc.teamcode.libs.core.Module;

public class IntakeSystem extends Module {
    private final MotorWrapper motor1;
    private final ServoWrapper servo1;
    private final ServoWrapper servo2;

    public IntakeSystem(BaseOpMode opMode, MotorWrapper motor1, ServoWrapper servo1, ServoWrapper servo2) {

        super(opMode);

        this.motor1 = new MotorWrapper(opMode, "intakeMotor");
        this.servo1 = new ServoWrapper(opMode, "leftIntakeServo", ServoWrapper.ServoMode.CONTINUOUS);
        this.servo2 = new ServoWrapper(opMode, "rightIntakeServo", ServoWrapper.ServoMode.CONTINUOUS);

    }

    @Override
    protected void init() {

        servo1.setReversed(true);

    }


    @Override
    protected void init_loop() {


    }

    @Override
    protected void loop() {

    }

    @Override
    protected void control_loop() {

    }



    @Override
    protected void start() {

    }

    @Override
    protected void stop() {

    }
}
