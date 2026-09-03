package org.firstinspires.ftc.teamcode.core.util;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Wraps the standard FTC {@link Gamepad} hardware class to provide enhanced button tracking
 * via {@link GamepadButton} (including rising/falling edge detection) alongside analog
 * stick and trigger reads.
 *
 * @author John Daniher
 * @version 1.0
 */
public final class GamepadWrapper {
    /** The underlying Qualcomm hardware Gamepad instance being wrapped. */
    private final Gamepad gamepad;

    /** Button state tracker for D-Pad Up. */
    private final GamepadButton dpadUp;

    /** Button state tracker for D-Pad Down. */
    private final GamepadButton dpadDown;

    /** Button state tracker for D-Pad Left. */
    private final GamepadButton dpadLeft;

    /** Button state tracker for D-Pad Right. */
    private final GamepadButton dpadRight;

    /** Button state tracker for the A button. */
    private final GamepadButton aButton;

    /** Button state tracker for the B button. */
    private final GamepadButton bButton;

    /** Button state tracker for the X button. */
    private final GamepadButton xButton;

    /** Button state tracker for the Y button. */
    private final GamepadButton yButton;

    /** Button state tracker for the left bumper. */
    private final GamepadButton leftBumper;

    /** Button state tracker for the right bumper. */
    private final GamepadButton rightBumper;

    /** Button state tracker for pressing down the left stick. */
    private final GamepadButton leftStickButton;

    /** Button state tracker for pressing down the right stick. */
    private final GamepadButton rightStickButton;

    /** Button state tracker for the Start button. */
    private final GamepadButton startButton;

    /** Button state tracker for the Guide (Xbox/Logitech center) button. */
    private final GamepadButton guideButton;

    /** Button state tracker for the Back button. */
    private final GamepadButton backButton;

    /** The current X-axis position of the left stick (-1.0 to 1.0). */
    private float leftStickX;

    /** The current Y-axis position of the left stick (-1.0 to 1.0). */
    private float leftStickY;

    /** The current X-axis position of the right stick (-1.0 to 1.0). */
    private float rightStickX;

    /** The current Y-axis position of the right stick (-1.0 to 1.0). */
    private float rightStickY;

    /** The current analog position of the left trigger (0.0 to 1.0). */
    private float leftTriggerPressure;

    /** The current analog position of the right trigger (0.0 to 1.0). */
    private float rightTriggerPressure;

    /**
     * Constructs a new GamepadWrapper around a standard FTC Gamepad object.
     *
     * @param gamepad The hardware {@link Gamepad} instance from an OpMode.
     */
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

    /**
     * Polls and updates the state of all buttons, joysticks, and triggers.
     * <p>
     * Must be called in every loop iteration of your OpMode to ensure state detection
     * (such as {@code justPressed()}) works accurately.
     * </p>
     */
    public void update() {
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

    /**
     * Gets the wrapped button state for D-Pad Up.
     *
     * @return The {@link GamepadButton} instance for D-Pad Up.
     */
    public GamepadButton getDpadUp() {
        return dpadUp;
    }

    /**
     * Gets the wrapped button state for D-Pad Down.
     *
     * @return The {@link GamepadButton} instance for D-Pad Down.
     */
    public GamepadButton getDpadDown() {
        return dpadDown;
    }

    /**
     * Gets the wrapped button state for D-Pad Left.
     *
     * @return The {@link GamepadButton} instance for D-Pad Left.
     */
    public GamepadButton getDpadLeft() {
        return dpadLeft;
    }

    /**
     * Gets the wrapped button state for D-Pad Right.
     *
     * @return The {@link GamepadButton} instance for D-Pad Right.
     */
    public GamepadButton getDpadRight() {
        return dpadRight;
    }

    /**
     * Gets the wrapped button state for the A button.
     *
     * @return The {@link GamepadButton} instance for the A button.
     */
    public GamepadButton getA() {
        return aButton;
    }

    /**
     * Gets the wrapped button state for the B button.
     *
     * @return The {@link GamepadButton} instance for the B button.
     */
    public GamepadButton getB() {
        return bButton;
    }

    /**
     * Gets the wrapped button state for the X button.
     *
     * @return The {@link GamepadButton} instance for the X button.
     */
    public GamepadButton getX() {
        return xButton;
    }

    /**
     * Gets the wrapped button state for the Y button.
     *
     * @return The {@link GamepadButton} instance for the Y button.
     */
    public GamepadButton getY() {
        return yButton;
    }

    /**
     * Gets the wrapped button state for the left bumper.
     *
     * @return The {@link GamepadButton} instance for the left bumper.
     */
    public GamepadButton getLeftBumper() {
        return leftBumper;
    }

    /**
     * Gets the wrapped button state for the right bumper.
     *
     * @return The {@link GamepadButton} instance for the right bumper.
     */
    public GamepadButton getRightBumper() {
        return rightBumper;
    }

    /**
     * Gets the wrapped button state for pressing the left stick button.
     *
     * @return The {@link GamepadButton} instance for the left stick button.
     */
    public GamepadButton getLeftStickButton() {
        return leftStickButton;
    }

    /**
     * Gets the wrapped button state for pressing the right stick button.
     *
     * @return The {@link GamepadButton} instance for the right stick button.
     */
    public GamepadButton getRightStickButton() {
        return rightStickButton;
    }

    /**
     * Gets the wrapped button state for the Start button.
     *
     * @return The {@link GamepadButton} instance for the Start button.
     */
    public GamepadButton getStartButton() {
        return startButton;
    }

    /**
     * Gets the wrapped button state for the Guide button.
     *
     * @return The {@link GamepadButton} instance for the Guide button.
     */
    public GamepadButton getGuideButton() {
        return guideButton;
    }

    /**
     * Gets the wrapped button state for the Back button.
     *
     * @return The {@link GamepadButton} instance for the Back button.
     */
    public GamepadButton getBackButton() {
        return backButton;
    }

    /**
     * Gets the horizontal axis position of the left joystick.
     *
     * @return Float value from -1.0 (left) to 1.0 (right).
     */
    public float getLeftStickX() {
        return leftStickX;
    }

    /**
     * Gets the vertical axis position of the left joystick.
     *
     * @return Float value from -1.0 (top) to 1.0 (bottom).
     */
    public float getLeftStickY() {
        return leftStickY;
    }

    /**
     * Gets the horizontal axis position of the right joystick.
     *
     * @return Float value from -1.0 (left) to 1.0 (right).
     */
    public float getRightStickX() {
        return rightStickX;
    }

    /**
     * Gets the vertical axis position of the right joystick.
     *
     * @return Float value from -1.0 (top) to 1.0 (bottom).
     */
    public float getRightStickY() {
        return rightStickY;
    }

    /**
     * Gets the current displacement of the left analog trigger.
     *
     * @return Float value from 0.0 (unpressed) to 1.0 (fully pressed).
     */
    public float getLeftTriggerPressure() {
        return leftTriggerPressure;
    }

    /**
     * Gets the current displacement of the right analog trigger.
     *
     * @return Float value from 0.0 (unpressed) to 1.0 (fully pressed).
     */
    public float getRightTriggerPressure() {
        return rightTriggerPressure;
    }
}