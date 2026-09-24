package org.firstinspires.ftc.teamcode.libs.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.BioBuzz.IntakeSystem;
import org.firstinspires.ftc.teamcode.libs.core.BaseOpMode;
import org.firstinspires.ftc.teamcode.libs.util.DriverMenu;

@TeleOp(name = "Intake System", group = "tests")
public class IntakeSystemTest extends BaseOpMode {
    IntakeSystem intakeSystem;



    @Override
    public DriverMenu initDriverMenu() {
        return null;
    }

    @Override
    public void initModules() {
        intakeSystem = new IntakeSystem(this);
        modules.add(intakeSystem);
    }
}
