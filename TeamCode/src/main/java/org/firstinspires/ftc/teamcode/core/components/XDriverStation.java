package org.firstinspires.ftc.teamcode.core.components;

import com.qualcomm.robotcore.hardware.Gamepad;

public class XDriverStation {

    private final XGamepad controller1;
    private final XGamepad controller2;

    private boolean dualPlayer;

    public XDriverStation(Gamepad gamepad1, Gamepad gamepad2) {

        controller1 = new XGamepad(gamepad1);
        controller2 = new XGamepad(gamepad2);

    }

    public void update(){

        controller1.updateValues();

        if (dualPlayer) {

            controller2.updateValues();

        }

        if (controller1.getBackButton().justPressed()) {

            dualPlayer = !dualPlayer;

        }

    }

    public XGamepad getController1() {

        return controller1;

    }

    public XGamepad getController2() {

        return controller2;

    }
    
}
