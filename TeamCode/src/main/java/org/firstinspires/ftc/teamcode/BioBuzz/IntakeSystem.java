package org.firstinspires.ftc.teamcode.BioBuzz;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.libs.components.MotorWrapper;
import org.firstinspires.ftc.teamcode.libs.components.ServoWrapper;
import org.firstinspires.ftc.teamcode.libs.core.BaseOpMode;
import org.firstinspires.ftc.teamcode.libs.core.Module;
public class IntakeSystem extends Module {

    private final MotorWrapper intakeMotor;
    private final ServoWrapper servo1;
    private final ServoWrapper servo2;

    private boolean activated;

    public IntakeSystem(BaseOpMode opMode) {

        super(opMode);

        intakeMotor = new MotorWrapper(opMode, "intakeMotor");
        servo1 = new ServoWrapper(opMode, "leftIntakeServo", ServoWrapper.ServoMode.CONTINUOUS);
        servo2 = new ServoWrapper(opMode, "rightIntakeServo", ServoWrapper.ServoMode.CONTINUOUS);

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

        if(activated){
            intakeMotor.setPower(1.0);
            servo1.setServoPower(1.0);
            servo2.setServoPower(1.0);

        } else {
            intakeMotor.stop();
            servo1.stopServo();
            servo2.stopServo();

        }

    }

    @Override
    protected void control_loop() {

        if(opMode.getDriverStation().getGamepad1().getA().justPressed()){

            activated = !activated;

        }

    }



    @Override
    protected void start() {

    }

    @Override
    protected void stop() {
        intakeMotor.stop();
        servo1.stopServo();
        servo2.stopServo();

    }

}
