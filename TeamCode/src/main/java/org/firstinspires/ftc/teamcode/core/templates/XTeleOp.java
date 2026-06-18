package org.firstinspires.ftc.teamcode.core.templates;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.core.components.DriverStation;
import org.firstinspires.ftc.teamcode.core.util.Scheduler;

public abstract class XTeleOp extends OpMode implements XOpMode {

    private DriverStation driverStation;
    private Scheduler scheduler;

    private ArrayList<>

    @Override
    public void init(){

        driverStation = new DriverStation(gamepad1, gamepad2);
        scheduler = new Scheduler();

        init_modules();

    }

    @Override
    public void init_loop(){



    }

    @Override
    public void start(){



    }

    @Override
    public void loop(){




    }


    @Override
    public void stop(){



    }

    public abstract void init_modules();







}
