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
    private double throttle;

    private boolean option;
    private boolean safe;

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

        if(safe){
            intakeMotor.setPower(throttle);
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
       if(activated) {
           if (opMode.getDriverStation().getGamepad1().getY().justPressed()) {
               option = !option;
               throttle = 0;
           }

           if (option) {
               throttle = opMode.getDriverStation().getGamepad1().getLeftStickY();
           } else if (!option) {
               if (opMode.getDriverStation().getGamepad1().getDpadUp().justPressed()) {
                   if (throttle != 1) {
                       throttle += .1;
                   }
               }
           }
           if (opMode.getDriverStation().getGamepad1().getDpadDown().justPressed()) {
               if (throttle != 0) {
                   throttle -= .1;
               }
           }
       }
        if(opMode.getDriverStation().getGamepad1().getA().justPressed()){

            activated = !activated;

        }
        if(opMode.getDriverStation().getGamepad1().getB().justPressed()) {
            safe = !safe;
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
