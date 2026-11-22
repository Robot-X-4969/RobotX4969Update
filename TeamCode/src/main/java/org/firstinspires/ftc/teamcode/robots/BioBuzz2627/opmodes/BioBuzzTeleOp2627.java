package org.firstinspires.ftc.teamcode.robots.BioBuzz2627.opmodes;

import static java.lang.Math.PI;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.libs.core.Module;
import org.firstinspires.ftc.teamcode.libs.core.RobotTeleOp;
import org.firstinspires.ftc.teamcode.libs.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.libs.util.DriverMenu;
import org.firstinspires.ftc.teamcode.libs.util.MenuBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class BioBuzzTeleOp2627 extends RobotTeleOp {


    @Override
    public DriverMenu initDriverMenu() {

        return MenuBuilder.createMenu(this)
                .addSubMenu("Starting Position")
                    .addAction("Red Left", () -> {
                        ((MecanumDrive) Objects.requireNonNull(modules.get("drive"))).getPinpoint().setStartingPose(0, 0, -PI/2.0);
                    })
                    .addAction("Red Right", () -> {
                        ((MecanumDrive) Objects.requireNonNull(modules.get("drive"))).getPinpoint().setStartingPose(0, 0, PI/2.0);
                    })
                    .addAction("Blue Left", () -> {
                    ((MecanumDrive) Objects.requireNonNull(modules.get("drive"))).getPinpoint().setStartingPose(0, 0, PI/2.0);
                    })
                    .addAction("Blue Right", () -> {
                        ((MecanumDrive) Objects.requireNonNull(modules.get("drive"))).getPinpoint().setStartingPose(0, 0, -PI/2.0);
                    })
                .endSubMenu()
                .buildMenu();
    }

    @Override
    public HashMap<String, Module> initModules() {

        HashMap <String, Module> modules = new HashMap<>();
        MecanumDrive mecanumDrive = new MecanumDrive(this);

        modules.put("drive", mecanumDrive);




        return modules;

    }


}
