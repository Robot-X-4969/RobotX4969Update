package org.firstinspires.ftc.teamcode.libs.core;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.libs.util.DriverMenu;
import org.firstinspires.ftc.teamcode.libs.util.DriverStation;
import org.firstinspires.ftc.teamcode.libs.util.LogEntry;
import org.firstinspires.ftc.teamcode.libs.util.Scheduler;

import java.util.ArrayList;
import java.util.HashMap;

public interface BaseOpMode {

    DriverMenu initDriverMenu();
    void initModules();
    HardwareMap getHardwareMap();
    void logData(LogEntry logEntry);
    DriverStation getDriverStation();
    Scheduler getScheduler();
    HashMap<String, Module> getModules();
    Telemetry getTelemetry();
    boolean isInDebugMode();
    double getDeltaTime();

}
