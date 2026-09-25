package org.firstinspires.ftc.teamcode.libs.tests.StarterBot;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.libs.core.RobotTeleOp;
import org.firstinspires.ftc.teamcode.libs.drive.TankDrive;
import org.firstinspires.ftc.teamcode.libs.util.DriverMenu;

@TeleOp(name = "Throttle", group = "tests")
public class ThrottleTester extends RobotTeleOp {

    TankDrive tankDrive;
    @Override
    public DriverMenu initDriverMenu() {
        return null;
    }

    @Override
    public void initModules() {

        tankDrive = new TankDrive(this, 312);
        modules.add(tankDrive);

    }

}
