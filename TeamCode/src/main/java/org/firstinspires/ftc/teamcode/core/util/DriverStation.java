package org.firstinspires.ftc.teamcode.core.util;

import com.qualcomm.robotcore.hardware.Gamepad;

public final class DriverStation {

    private final GamepadWrapper gamepad1;
    private final GamepadWrapper gamepad2;

    private boolean dualGamepadMode;

    public DriverStation(Gamepad gamepad1, Gamepad gamepad2) {

        this.gamepad1 = new GamepadWrapper(gamepad1);
        this.gamepad2 = new GamepadWrapper(gamepad2);

        this.dualGamepadMode = false;

    }

    public void update(){

        gamepad1.update();

        if(dualGamepadMode){

            gamepad2.update();

        }

        if(gamepad1.getBackButton().justPressed()){

            dualGamepadMode = !dualGamepadMode;

        }

    }

    public GamepadWrapper getGamepad1() {

        return gamepad1;

    }

    public GamepadWrapper getGamepad2() {

        return gamepad2;

    }

}
