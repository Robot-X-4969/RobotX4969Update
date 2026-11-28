package org.firstinspires.ftc.teamcode.libs.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.libs.core.RobotTeleOp;
import org.firstinspires.ftc.teamcode.libs.util.DriverMenu;
import org.firstinspires.ftc.teamcode.robots.BioBuzz2627.SingleTurret.modules.Turret;

@TeleOp(name = "TurretTest", group = "Tests")

public class TurretTest extends RobotTeleOp {

    @Override
    public DriverMenu initDriverMenu() {
        return null;
    }

    @Override
    public void initModules() {

        modules.put("Turret", new Turret(this));

    }

}
