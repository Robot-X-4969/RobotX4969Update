package org.firstinspires.ftc.teamcode.core.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.core.core.BaseOpMode;
import org.firstinspires.ftc.teamcode.core.util.MenuBuilder;

@TeleOp(name = "Menu Builder Test", group = "Tests")
public final class MenuBuilderTest extends BaseOpMode {

    @Override
    public void initDriverMenu() {

        driverMenu = MenuBuilder.createMenu(this)
                .addSubMenu("Sub Menu 1")
                    .addAction("Action 1", () -> {
                        telemetry.log().add("Action 1 Executed");
                    })
                    .addAction("Action 2", () -> {
                        telemetry.log().add("Action 2 Executed");
                    })
                .endSubMenu()
                .addSubMenu("Sub Menu 2")
                    .addAction("Action 3", () -> {
                        telemetry.log().add("Action 3 Executed");
                    })
                    .addAction("Action 4", () -> {
                        telemetry.log().add("Action 4 Executed");
                    })
                .endSubMenu()
                .addAction("Action 5", () -> {
                    telemetry.log().add("Action 5 Executed");
                })
                .addAction("Action 6", () -> {
                    telemetry.log().add("Action 6 Executed");
                })
                .buildMenu();

    }

}
