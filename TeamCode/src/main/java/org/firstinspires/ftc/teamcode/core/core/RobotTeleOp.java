package org.firstinspires.ftc.teamcode.core.core;

public abstract class RobotTeleOp extends BaseOpMode {

    public RobotTeleOp() {

        super();

    }

    public void control_loop() {

        for(System system : systems) {

            system.control_loop();

        }

    }

    @Override
    public void loop(){

        telemetry.clearAll();

        scheduler.pollEvents();
        driverStation.update();

        control_loop();

        if(driverStation.getGamepad1().getRightStickButton().justPressed()){

            debugMode = !debugMode;

        }

        for(System system : systems){

            system.loop();

        }

        logger.sendTelemetry();

        telemetry.update();

    }

}
