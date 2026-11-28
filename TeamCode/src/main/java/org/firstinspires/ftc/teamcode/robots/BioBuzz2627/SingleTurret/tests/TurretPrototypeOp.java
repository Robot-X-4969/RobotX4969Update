package org.firstinspires.ftc.teamcode.robots.BioBuzz2627.SingleTurret.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.libs.core.RobotTeleOp;
import org.firstinspires.ftc.teamcode.libs.util.DriverMenu;
import org.firstinspires.ftc.teamcode.robots.BioBuzz2627.SingleTurret.modules.PrototypeTurret;

@TeleOp(name = "Turret Test", group = "Tests")
public class TurretPrototypeOp extends RobotTeleOp {


    @Override
    public DriverMenu initDriverMenu() {
        return null;
    }

    @Override
    public void initModules() {

        PrototypeTurret turret = new PrototypeTurret(this);

        modules.put("turret", turret);

    }

}
