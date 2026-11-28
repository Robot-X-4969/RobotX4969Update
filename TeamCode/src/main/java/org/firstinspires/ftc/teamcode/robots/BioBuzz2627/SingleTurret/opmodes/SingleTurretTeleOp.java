package org.firstinspires.ftc.teamcode.robots.BioBuzz2627.SingleTurret.opmodes;

import static java.lang.Math.PI;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.teamcode.libs.components.Pinpoint;
import org.firstinspires.ftc.teamcode.libs.core.Module;
import org.firstinspires.ftc.teamcode.libs.core.RobotTeleOp;
import org.firstinspires.ftc.teamcode.libs.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.libs.util.DriverMenu;
import org.firstinspires.ftc.teamcode.libs.util.MenuBuilder;
import org.firstinspires.ftc.teamcode.robots.BioBuzz2627.SingleTurret.modules.Turret;

import java.util.HashMap;

public class SingleTurretTeleOp extends RobotTeleOp {

    Pinpoint pinpoint;

    public SingleTurretTeleOp(){

        pinpoint = new Pinpoint(
                this,
                "pinpoint",
                GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD,
                0,
                0
        );

    }

    @Override
    public DriverMenu initDriverMenu() {

        return MenuBuilder.createMenu(this)
                .addSubMenu("Starting Position")
                    .addAction("Red Left", () -> {

                        ((MecanumDrive) modules.get("drive")).setDriverOffset(90.0);

                    })
                    .addAction("Red Right", () -> {

                        ((MecanumDrive) modules.get("drive")).setDriverOffset(90.0);

                    })
                    .addAction("Blue Left", () -> {

                        ((MecanumDrive) modules.get("drive")).setDriverOffset(-90.0);

                    })
                    .addAction("Blue Right", () -> {

                        ((MecanumDrive) modules.get("drive")).setDriverOffset(-90.0);

                    })
                .endSubMenu()
                .buildMenu();

    }

    @Override
    public void initModules() {

        MecanumDrive mecanumDrive = new MecanumDrive(this, pinpoint);
        Turret turret = new Turret(this, pinpoint);



        modules.put("drive", mecanumDrive);
        modules.put("turret", turret);


    }

}

