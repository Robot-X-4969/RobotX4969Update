package org.firstinspires.ftc.teamcode.core.templates;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.core.util.DriverStation;
import org.firstinspires.ftc.teamcode.core.util.Scheduler;

import java.util.ArrayList;

public abstract class RobotTeleOp extends BaseOpMode {


    public RobotTeleOp() {

        super();

    }

    @Override
    public void init() {

        super.init();

    }

    @Override
    public void init_loop() {

        super.init_loop();

    }

    @Override
    public void loop(){

        super.loop();

    }

    @Override
    public void stop(){







    }

    public HardwareMap getHardwareMap(){

        return hardwareMap;

    }
    public DriverStation getDriverStation(){

        return driverStation;

    }
    public Scheduler getScheduler() {

        return scheduler;

    }
    public Telemetry getTelemetry(){

        return telemetry;

    }
    public ArrayList<String> getLog(){

        return log;

    }

}
