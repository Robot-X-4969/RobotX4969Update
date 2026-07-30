package org.firstinspires.ftc.teamcode.core.util;

import com.qualcomm.robotcore.hardware.Gamepad;

public final class GamepadWrapper {
    private final Gamepad gamepad;
    private final GamepadButton dpadUp;
    private final GamepadButton dpadDown;
    private final GamepadButton dpadLeft;
    private final GamepadButton dpadRight;
    private final GamepadButton aButton;
    private final GamepadButton bButton;
    private final GamepadButton xButton;
    private final GamepadButton yButton;
    private final GamepadButton leftBumper;
    private final GamepadButton rightBumper;
    private final GamepadButton leftStickButton;
    private final GamepadButton rightStickButton;
    private final GamepadButton startButton;
    private final GamepadButton guideButton;
    private final GamepadButton backButton;

    private float leftStickX;
    private float leftStickY;
    private float rightStickX;
    private float rightStickY;

    private float leftTriggerPressure;
    private float rightTriggerPressure;

    public GamepadWrapper(Gamepad gamepad) {

        this.gamepad = gamepad;

        dpadUp = new GamepadButton();
        dpadDown = new GamepadButton();
        dpadLeft = new GamepadButton();
        dpadRight = new GamepadButton();
        aButton = new GamepadButton();
        bButton = new GamepadButton();
        xButton = new GamepadButton();
        yButton = new GamepadButton();
        leftBumper = new GamepadButton();
        rightBumper = new GamepadButton();
        leftStickButton = new GamepadButton();
        rightStickButton = new GamepadButton();
        startButton = new GamepadButton();
        guideButton = new GamepadButton();
        backButton = new GamepadButton();

        leftStickX = 0.0f;
        leftStickY = 0.0f;
        rightStickX = 0.0f;
        rightStickY = 0.0f;

        leftTriggerPressure = 0.0f;
        rightTriggerPressure = 0.0f;

    }

    public void update(){

        dpadUp.update(gamepad.dpad_up);
        dpadDown.update(gamepad.dpad_down);
        dpadLeft.update(gamepad.dpad_left);
        dpadRight.update(gamepad.dpad_right);
        aButton.update(gamepad.a);
        bButton.update(gamepad.b);
        xButton.update(gamepad.x);
        yButton.update(gamepad.y);
        leftBumper.update(gamepad.left_bumper);
        rightBumper.update(gamepad.right_bumper);
        leftStickButton.update(gamepad.left_stick_button);
        rightStickButton.update(gamepad.right_stick_button);
        startButton.update(gamepad.start);
        guideButton.update(gamepad.guide);
        backButton.update(gamepad.back);

        leftStickX = gamepad.left_stick_x;
        leftStickY = gamepad.left_stick_y;
        rightStickX = gamepad.right_stick_x;
        rightStickY = gamepad.right_stick_y;

        leftTriggerPressure = gamepad.left_trigger;
        rightTriggerPressure = gamepad.right_trigger;

    }

    public GamepadButton getDpadUp() {

        return dpadUp;

    }

    public GamepadButton getDpadDown() {

        return dpadDown;

    }

    public GamepadButton getDpadLeft() {

        return dpadLeft;

    }

    public GamepadButton getDpadRight() {

        return dpadRight;

    }

    public GamepadButton getA() {

        return aButton;

    }

    public GamepadButton getB() {

        return bButton;
    }

    public GamepadButton getX() {

        return xButton;

    }

    public GamepadButton getY() {

        return yButton;

    }

    public GamepadButton getLeftBumper() {

        return leftBumper;

    }

    public GamepadButton getRightBumper() {

        return rightBumper;

    }

    public GamepadButton getLeftStickButton() {

        return leftStickButton;

    }

    public GamepadButton getRightStickButton() {

        return rightStickButton;

    }

    public GamepadButton getStartButton() {

        return startButton;

    }

    public GamepadButton getGuideButton() {

        return guideButton;

    }

    public GamepadButton getBackButton() {

        return backButton;

    }

    public float getLeftStickX() {

        return leftStickX;

    }

    public float getLeftStickY() {

        return leftStickY;

    }

    public float getRightStickX() {

        return rightStickX;

    }

    public float getRightStickY() {

        return rightStickY;

    }

    public float getLeftTriggerPressure() {

        return leftTriggerPressure;

    }

    public float getRightTriggerPressure() {

        return rightTriggerPressure;

    }

}

