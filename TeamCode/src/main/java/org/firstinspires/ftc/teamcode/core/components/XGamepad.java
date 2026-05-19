package org.firstinspires.ftc.teamcode.core.components;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.core.util.Button;
public class XGamepad {

    private float leftStickX;
    private float leftStickY;
    private float rightStickX;
    private float rightStickY;

    private float leftTriggerPressure;
    private float rightTriggerPressure;

    private final Button dPadUp;
    private final Button dPadDown;
    private final Button dPadLeft;
    private final Button dPadRight;

    private final Button aButton;
    private final Button bButton;
    private final Button xButton;
    private final Button yButton;

    private final Button leftBumper;
    private final Button rightBumper;

    private final Button leftStickButton;
    private final Button rightStickButton;

    private final Button startButton;
    private final Button backButton;
    private final Button guideButton;

    private final Gamepad gamepad;

    public XGamepad(Gamepad gamepad) {

        this.dPadUp = new Button();
        this.dPadDown = new Button();
        this.dPadLeft = new Button();
        this.dPadRight = new Button();

        this.aButton = new Button();
        this.bButton = new Button();
        this.xButton = new Button();
        this.yButton = new Button();

        this.leftBumper = new Button();
        this.rightBumper = new Button();

        this.leftStickButton = new Button();
        this.rightStickButton = new Button();

        this.startButton = new Button();
        this.backButton = new Button();
        this.guideButton = new Button();

        this.gamepad = gamepad;

    }

    public void update(){

        leftStickX = gamepad.left_stick_x;
        leftStickY = gamepad.left_stick_y;
        rightStickX = gamepad.right_stick_x;
        rightStickY = gamepad.right_stick_y;

        leftTriggerPressure = gamepad.left_trigger;
        rightTriggerPressure = gamepad.right_trigger;

        dPadUp.update(gamepad.dpad_up);
        dPadDown.update(gamepad.dpad_down);
        dPadLeft.update(gamepad.dpad_left);
        dPadRight.update(gamepad.dpad_right);

        aButton.update(gamepad.a);
        bButton.update(gamepad.b);
        xButton.update(gamepad.x);
        yButton.update(gamepad.y);

        leftBumper.update(gamepad.left_bumper);
        rightBumper.update(gamepad.right_bumper);

        leftStickButton.update(gamepad.left_stick_button);
        rightStickButton.update(gamepad.right_stick_button);

        startButton.update(gamepad.start);
        backButton.update(gamepad.back);
        guideButton.update(gamepad.guide);


    }




}
