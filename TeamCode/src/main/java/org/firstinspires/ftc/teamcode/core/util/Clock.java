package org.firstinspires.ftc.teamcode.core.util;

/**
 * A versatile clock class that combines the functions of both a stopwatch and a timer.
 *
 * <p>
 * The class uses the system's high-resolution time source (nanoseconds) to provide accurate timing for both modes.
 * It allows you to start, pause, resume, and reset the clock, as well as retrieve elapsed time and remaining time (for timers).
 * </p>
 *
 * <p>
 * The class uses <b>elapsed time</b> as the foundational source of time keeping, because a timer is just a stopwatch with a target.
 * This allows consistent behavior across both modes and simplifies the implementation.
 * </p>
 *
 * @author Evan (original stopwatch implementation)
 * @author John Daniher(refactor)
 * @author Gavin Farrell (refactor into Clock class)
 * @version 3.0
 * @since 2026/06
 *
 */
public final class Clock {

    /**Defines the operational modes of the Clock utility.*/
    private enum Mode {STOPWATCH, TIMER}
    /** Defines the possible states of the Clock utility.*/
    private enum ClockState {RUNNING, PAUSED, STOPPED}

    /**The operational mode of the clock (STOPWATCH or TIMER).*/
    private final Mode mode;
    /**The target duration in nanoseconds for TIMER mode. For STOPWATCH mode, this is set to 0 and ignored.*/
    private final long targetDuration;
    /**The current state of the clock (RUNNING, PAUSED, or STOPPED).*/
    private ClockState state;
    /**The starting time of the clock in nanoseconds.*/
    private long startTime;
    /**The accumulated time in nanoseconds, used to preserve elapsed time when pausing and resuming the clock.*/
    private long accumulatedTime;

    /**
     * Constructs a new Clock instance with the specified mode and duration.
     *
     * @param mode The operational mode of the clock (STOPWATCH or TIMER).
     * @param duration The target duration in milliseconds for TIMER mode. Ignored for STOPWATCH mode.
     */
    private Clock(Mode mode, long duration) {

        this.mode = mode;

        this.targetDuration = duration * 1_000_000; // Convert milliseconds to nanoseconds

        this.state = ClockState.STOPPED;

    }

    /**
     * Creates a new Clock instance in TIMER mode with the specified duration.
     * @param duration The target duration in milliseconds for the timer.
     * @return A new Clock instance configured as a timer with the specified duration in milliseconds.
     */
    public static Clock asTimer(long duration){

        return new Clock(Mode.TIMER, duration);

    }

    /**
     * Creates a new Clock instance in STOPWATCH mode.
     * @return A new Clock instance configured as a stopwatch.
     */
    public static Clock asStopwatch(){

        return new Clock(Mode.STOPWATCH, 0);

    }

    /**
     * Starts the clock.
     *
     * <p>
     * This method is used internally when initializing the clock and can also be called after resetting the clock to start it again.
     * </p>
     */
    public void start(){

        if(state == ClockState.STOPPED){

            startTime = System.nanoTime();
            accumulatedTime = 0;
            state = ClockState.RUNNING;

        }

    }

    /**
     * Pauses the clock, preserving the elapsed time up to the point of pausing.
     */
    public void pause(){

        long elapsedTime = getElapsedNanoTime();

        if(state == ClockState.RUNNING){

            state = ClockState.PAUSED;
            accumulatedTime = elapsedTime;

        }

    }

    /**
     * Resumes the clock from a paused state.
     */
    public void resume(){

        if(state == ClockState.PAUSED) {

            state = ClockState.RUNNING;
            startTime = System.nanoTime();

        }

    }

    /**
     * Resets the clock to its initial state, clearing all elapsed time and stopping the clock.
     */
    public void reset(){

        startTime = System.nanoTime();
        accumulatedTime = 0;
        state = ClockState.STOPPED;

    }

    /**
     * Retrieves the elapsed time in nanoseconds since the clock was started.
     *
     * @return The elapsed time in nanoseconds.
     */
    public long getElapsedNanoTime(){

        if(state == ClockState.RUNNING){

            return (System.nanoTime() - startTime) + accumulatedTime;

        }

        return accumulatedTime;

    }

    /**
     * Retrieves the elapsed time in milliseconds since the clock was started; used as a helper function for returning time in a more human-readable format.
     *
     * @return The elapsed time in milliseconds.
     */
    public long getElapsedMilliTime(){

        return getElapsedNanoTime() / 1_000_000;

    }

    /**
     * Retrieves the remaining time in milliseconds until the target duration is reached (for TIMER mode).
     *
     * @return The remaining time in milliseconds, or 0 if the target duration has been reached or exceeded.
     */
    public long getRemainingTime(){

        if(mode != Mode.TIMER){

        }

        return Math.max(0, (targetDuration - getElapsedNanoTime()) / 1_000_000);

    }

    /**
     * Checks if the timer has reached its target duration (for TIMER mode).
     *
     * @return True if the remaining time is 0, indicating that the timer has completed; otherwise, false.
     */
    public boolean isTimerDone(){

        if(mode != Mode.TIMER){

        }

        return getRemainingTime() == 0;

    }

}
