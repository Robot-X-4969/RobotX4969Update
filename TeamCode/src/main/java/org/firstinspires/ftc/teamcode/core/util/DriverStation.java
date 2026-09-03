package org.firstinspires.ftc.teamcode.core.util;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Manages the driver station gamepads for FTC robotics programs.
 * <p>
 * Handles wrapped gamepad instances for enhanced input handling and allows
 * dynamically toggling between single-gamepad and dual-gamepad control modes.
 * </p>
 *
 * @author John Daniher
 * @author Gavin Farrell (update)
 * @version 2.0
 */
public final class DriverStation {

    /** The wrapped instance of the primary gamepad (Driver 1). */
    private GamepadWrapper gamepad1;

    /** The wrapped instance of the secondary gamepad (Driver 2). */
    private GamepadWrapper gamepad2;

    /** Tracks whether dual-gamepad mode is active (true) or single-gamepad mode is active (false). */
    private boolean dualGamepadMode;

    public DriverStation() {

        this.dualGamepadMode = false;

    }
    /**
     * Constructs a new DriverStation instance wrapping two hardware gamepads.
     *
     * @param gamepad1 The primary FTC hardware gamepad (gamepad1).
     * @param gamepad2 The secondary FTC hardware gamepad (gamepad2).
     */
    public DriverStation(Gamepad gamepad1, Gamepad gamepad2) {

        this.gamepad1 = new GamepadWrapper(gamepad1);
        this.gamepad2 = new GamepadWrapper(gamepad2);

        this.dualGamepadMode = false;

    }

    /**
     * Updates the button and stick states for the active gamepads.
     * <p>
     * Always updates {@code gamepad1}, and conditionally updates {@code gamepad2}
     * if dual-gamepad mode is enabled. Also toggles dual-gamepad mode when the
     * back button on {@code gamepad1} is pressed.
     * </p>
     */
    public void update() {
        gamepad1.update();

        if (dualGamepadMode) {
            gamepad2.update();
        }

        if (gamepad1.getBackButton().justPressed()) {
            dualGamepadMode = !dualGamepadMode;
        }
    }

    public void setGamepads(Gamepad gamepad1, Gamepad gamepad2) {

        this.gamepad1 = new GamepadWrapper(gamepad1);
        this.gamepad2 = new GamepadWrapper(gamepad2);

    }

    /**
     * Gets the wrapped primary gamepad.
     *
     * @return The {@link GamepadWrapper} instance for gamepad 1.
     */
    public GamepadWrapper getGamepad1() {
        return gamepad1;
    }

    /**
     * Gets the wrapped secondary gamepad.
     *
     * @return The {@link GamepadWrapper} instance for gamepad 2.
     */
    public GamepadWrapper getGamepad2() {
        return gamepad2;
    }

}