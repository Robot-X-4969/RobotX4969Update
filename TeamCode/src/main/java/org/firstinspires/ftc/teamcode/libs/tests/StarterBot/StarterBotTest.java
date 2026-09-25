package org.firstinspires.ftc.teamcode.libs.tests.StarterBot;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.BioBuzz.IntakeSystem;
import org.firstinspires.ftc.teamcode.libs.core.BaseOpMode;
import org.firstinspires.ftc.teamcode.libs.drive.TankDrive;
import org.firstinspires.ftc.teamcode.libs.util.DriverMenu;

@TeleOp(name = "Intake System", group = "tests")
public class StarterBotTest extends BaseOpMode {
    IntakeSystem intakeSystem;
    TankDrive tankDrive;



    @Override
    public DriverMenu initDriverMenu() {
        return null;
    }

    @Override
    public void initModules() {
        intakeSystem = new IntakeSystem(this);
        tankDrive = new TankDrive(this, 312);
        modules.add(tankDrive);
        modules.add(intakeSystem);
    }
}
