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
 * @author Evan (original implementation)
 * @author Gavin Farrell (refactor into Clock class)
 * @version 2.0
 * @since 2026/06
 *
 */
public class Clock {

    /**
     * Defines the operational modes of the Clock utility.
     */
    public enum Mode {

        ///STOPWATCH mode counts up from zero, allowing you to measure elapsed time since the clock was started.
        STOPWATCH,

        ///TIMER mode counts down from a specified duration, allowing you to measure remaining time until the target is reached.
        TIMER

    }

    /// The starting time of the clock in nanoseconds.
    private long startTime;
    /// The accumulated time in nanoseconds, used to preserve elapsed time when pausing and resuming the clock.
    private long accumulatedTime;
    /// The target duration in nanoseconds for TIMER mode. For STOPWATCH mode, this is set to 0 and ignored.
    private long targetDuration;
    /// A flag indicating whether the clock is currently running (true) or paused/stopped (false).
    private boolean isRunning;

    /**
     * Constructs and immediately starts a new Clock instance with the specified mode and duration.
     *
     * @param mode The operational mode of the clock (STOPWATCH or TIMER).
     * @param duration The target duration in milliseconds for TIMER mode. Ignored for STOPWATCH mode.
     */
    public Clock(Mode mode, long duration){

        if(mode == Mode.TIMER){

            targetDuration = duration * 1_000_000;
            start();

        } else if (mode == Mode.STOPWATCH){

            targetDuration = 0;
            start();

        }

    }

    /**
     * Retrieves the elapsed time in nanoseconds since the clock was started.
     *
     * @return The elapsed time in nanoseconds.
     */
    public long getElapsedNanoTime(){

        if(isRunning){

            return (System.nanoTime() - startTime) + accumulatedTime;

        }

        return accumulatedTime;

    }

    /**
     * Retrieves the elapsed time in milliseconds since the clock was started.
     *
     * @return The elapsed time in milliseconds.
     */
    public long getElapsedMilliTime(){

        return getElapsedNanoTime() / 1_000_000;

    }

    /**
     * Starts the clock.
     *
     * <p>
     * This method is used internally when initializing the clock and can also be called after resetting the clock to start it again.
     * </p>
     */
    public void start(){

        startTime = System.nanoTime();
        accumulatedTime = 0;
        isRunning = true;

    }

    /**
     * Pauses the clock, preserving the elapsed time up to the point of pausing.
     */
    public void pause(){

        long elapsedTime = getElapsedNanoTime();

        if(isRunning){

            isRunning = false;
            accumulatedTime = elapsedTime;

        }

    }

    /**
     * Resumes the clock from a paused state.
     */
    public void resume(){

        if(!isRunning) {

            isRunning = true;
            startTime = System.nanoTime();

        }

    }

    /**
     * Resets the clock to its initial state, clearing all elapsed time and stopping the clock.
     */
    public void reset(){

        startTime = System.nanoTime();
        accumulatedTime = 0;
        isRunning = false;

    }

    /**
     * Retrieves the remaining time in milliseconds until the target duration is reached (for TIMER mode).
     *
     * @return The remaining time in milliseconds, or 0 if the target duration has been reached or exceeded.
     */
    public long getRemainingTime(){

        return Math.max(0, (targetDuration - getElapsedNanoTime()) / 1_000_000);

    }

    /**
     * Checks if the timer has reached its target duration (for TIMER mode).
     *
     * @return True if the remaining time is 0, indicating that the timer has completed; otherwise, false.
     */
    public boolean isTimerDone(){

        return getRemainingTime() == 0;

    }

}
