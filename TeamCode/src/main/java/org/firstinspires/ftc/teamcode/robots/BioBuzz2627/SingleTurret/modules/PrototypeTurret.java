package org.firstinspires.ftc.teamcode.robots.BioBuzz2627.SingleTurret.modules;

import org.firstinspires.ftc.teamcode.libs.components.MotorWrapper;
import org.firstinspires.ftc.teamcode.libs.core.BaseOpMode;
import org.firstinspires.ftc.teamcode.libs.core.Module;
import org.firstinspires.ftc.teamcode.libs.util.LogEntry;

public class PrototypeTurret extends Module {

    private final MotorWrapper motor1;
    private final MotorWrapper motor2;

    private double power = 0.0;

    public PrototypeTurret(BaseOpMode opMode){

        super(opMode);

        motor1 = new MotorWrapper(opMode, "motor1", 0.0);
        motor2 = new MotorWrapper(opMode, "motor2", 0.0);

    }

    @Override
    protected void init() {

        motor1.setReverse(true);

        motor1.setPower(0.0);
        motor2.setPower(0.0);

    }

    @Override
    protected void init_loop() {

    }

    @Override
    protected void start() {

    }

    @Override
    protected void control_loop() {

        if(opMode.getDriverStation().getGamepad1().getLeftStickY() < 0.0){

            power += 0.01;

            if(power > 1.0){

                power = 1.0;

            }

        }
        if(opMode.getDriverStation().getGamepad1().getLeftStickY() > 0.0){

            power -= 0.01;

            if(power < 0.0){

                power = 0.0;

            }

        }

    }


    @Override
    protected void loop() {

        opMode.logData(new LogEntry(LogEntry.EntryType.DEBUG," " + power, "Power: "));
        motor1.setPower(power);
        motor2.setPower(power);
    }

    @Override
    protected void stop() {

        motor1.setPower(0.0);
        motor2.setPower(0.0);

    }

}
