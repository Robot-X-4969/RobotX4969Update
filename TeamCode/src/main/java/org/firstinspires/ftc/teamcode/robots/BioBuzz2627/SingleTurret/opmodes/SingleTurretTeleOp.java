package org.firstinspires.ftc.teamcode.robots.BioBuzz2627.SingleTurret.opmodes;

import static java.lang.Math.PI;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.teamcode.libs.components.Pinpoint;
import org.firstinspires.ftc.teamcode.libs.core.Module;
import org.firstinspires.ftc.teamcode.libs.core.RobotTeleOp;
import org.firstinspires.ftc.teamcode.libs.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.libs.util.DriverMenu;
import org.firstinspires.ftc.teamcode.libs.util.MenuBuilder;

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

                    })
                    .addAction("Red Right", () -> {

                    })
                    .addAction("Blue Left", () -> {

                    })
                    .addAction("Blue Right", () -> {

                    })
                .endSubMenu()
                .buildMenu();
    }

    @Override
    public void initModules() {

        HashMap <String, Module> modules = new HashMap<>();
        MecanumDrive mecanumDrive = new MecanumDrive(this);

        modules.put("drive", mecanumDrive);

    }

}

