package org.firstinspires.ftc.teamcode.libs.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.libs.core.Module;
import org.firstinspires.ftc.teamcode.libs.core.RobotTeleOp;
import org.firstinspires.ftc.teamcode.libs.util.DriverMenu;
import org.firstinspires.ftc.teamcode.libs.util.LogEntry;
import org.firstinspires.ftc.teamcode.libs.util.MenuBuilder;

import java.util.ArrayList;

@TeleOp(name = "Menu Builder Test", group = "Tests")
public final class MenuBuilderTest extends RobotTeleOp {

    @Override
    public DriverMenu initDriverMenu() {

        logger.logData(new LogEntry(LogEntry.EntryType.INFO, "Menu Builder Test History", null));
        logger.logData(new LogEntry(LogEntry.EntryType.DEBUG, "Menu Builder Test Debug: ", "debug"));


        return MenuBuilder.createMenu(this)
                .addSubMenu("Sub Menu 1")
                    .addAction("Action 1", () -> {
                        logger.logData(new LogEntry(LogEntry.EntryType.INFO, "Action 1 Executed", null));
                    })
                    .addAction("Action 2", () -> {
                        logger.logData(new LogEntry(LogEntry.EntryType.INFO, "Action 2 Executed", null));
                    })
                .endSubMenu()
                .addSubMenu("Sub Menu 2")
                    .addAction("Action 3", () -> {
                        logger.logData(new LogEntry(LogEntry.EntryType.INFO, "Action 3 Executed", null));
                    })
                    .addAction("Action 4", () -> {
                        logger.logData(new LogEntry(LogEntry.EntryType.INFO, "Action 4 Executed", null));
                    })
                .endSubMenu()
                .addAction("Action 5", () -> {
                    logger.logData(new LogEntry(LogEntry.EntryType.INFO, "Action 5 Executed", null));
                })
                .addAction("Action 6", () -> {
                    logger.logData(new LogEntry(LogEntry.EntryType.INFO, "Action 6 Executed", null));
                })
                .buildMenu();



    }

    @Override
    public void initModules() {



    }

}
