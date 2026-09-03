package org.firstinspires.ftc.teamcode.core.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.core.core.RobotTeleOp;
import org.firstinspires.ftc.teamcode.core.util.LogEntry;

@TeleOp(name = "Logging System Test", group = "Tests")
public final class LoggingSystemTest extends RobotTeleOp {

    int attribute = 0;

    @Override
    public void init() {

        super.init();
        logData(new LogEntry(LogEntry.EntryType.ERROR, "This is a test error entry", null));

    }

    @Override
    public void loop() {

        super.loop();

        attribute++;

        logData(new LogEntry(LogEntry.EntryType.DEBUG, Integer.toString(attribute), "This is a test debug entry" ));

        if(attribute > 1000000000){
            attribute = 0;
        }

    }

}
