package org.firstinspires.ftc.teamcode.libs.util;

/**
 * Wraps a single gamepad button state to provide edge detection (just pressed / just released)
 * and track pressing duration.
 *
 * @author John Daniher
 * @author Gavin Farrell (update)
 * @version 1.0
 */
public final class GamepadButton {

    /** Stopwatch used to measure how long the button remains continuously pressed. */
    private final Clock clock;

    /** The state (pressed/unpressed) of the button during the previous loop update. */
    private boolean lastState;

    /** The state (pressed/unpressed) of the button during the current loop update. */
    private boolean currentState;

    /** The duration (in nanoseconds) that the button was held during its most recent press cycle. */
    private long timePressed;

    private long timeReleased;

    /**
     * Constructs a new GamepadButton instance and initializes its internal stopwatch timer.
     */
    public GamepadButton() {clock = Clock.asStopwatch();}

    /**
     * Updates the current button state and manages the internal timer.
     * <p>
     * Should be called on every loop iteration with the raw boolean state from the hardware gamepad.
     * </p>
     *
     * @param newState The current boolean state of the hardware button ({@code true} if pressed).
     */
    public void update(boolean newState) {

        lastState = currentState;
        currentState = newState;

        updateTimePressedReleased();

    }

    /**
     * Checks if the button is currently being held down.
     *
     * @return {@code true} if the button is pressed, {@code false} otherwise.
     */
    public boolean isPressed() {return currentState;}

    /**
     * Checks if the button is currently not pressed.
     *
     * @return {@code true} if the button is released, {@code false} otherwise.
     */
    public boolean isReleased() {return !currentState;}

    /**
     * Checks if the button was pressed during the current frame (rising edge).
     *
     * @return {@code true} if the button transitioned from released to pressed this frame.
     */
    public boolean justPressed() {return currentState && !lastState;}

    /**
     * Checks if the button was released during the current frame (falling edge).
     *
     * @return {@code true} if the button transitioned from pressed to released this frame.
     */
    public boolean justReleased() {return lastState && !currentState;}

    public void updateTimePressedReleased(){

        if (justPressed()) {

            timePressed = 0;
            timeReleased = clock.getElapsedNanoTime();
            clock.reset();
            clock.start();

        } else if (isPressed()){

            timePressed = clock.getElapsedNanoTime();

        } else if(justReleased()) {

            timeReleased = 0;
            timePressed = clock.getElapsedNanoTime();
            clock.reset();
            clock.start();

        } else {

            timeReleased = clock.getElapsedNanoTime();

        }

    }

    public long getTimePressed() {return timePressed;}

    public long getTimeReleased() {return timeReleased;}

}