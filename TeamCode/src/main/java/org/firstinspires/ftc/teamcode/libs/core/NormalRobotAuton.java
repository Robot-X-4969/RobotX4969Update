package org.firstinspires.ftc.teamcode.libs.core;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.libs.util.DriverMenu;
import org.firstinspires.ftc.teamcode.libs.util.DriverStation;
import org.firstinspires.ftc.teamcode.libs.util.LogEntry;
import org.firstinspires.ftc.teamcode.libs.util.Scheduler;

import java.util.ArrayList;

public abstract class NormalRobotAuton extends LinearOpMode implements BaseOpMode{

    protected NormalRobotAuton(){



    }

    @Override
    public void runOpMode() throws InterruptedException {

    }


    @Override
    public DriverMenu initDriverMenu() {
        return null;
    }

    @Override
    public ArrayList<Module> initModules() {
        return null;
    }

    @Override
    public HardwareMap getHardwareMap() {
        return null;
    }

    @Override
    public void logData(LogEntry logEntry) {

    }

    @Override
    public DriverStation getDriverStation() {
        return null;
    }

    @Override
    public Scheduler getScheduler() {
        return null;
    }

    @Override
    public ArrayList<Module> getModules() {
        return null;
    }

    @Override
    public boolean isInDebugMode() {
        return false;
    }
}
