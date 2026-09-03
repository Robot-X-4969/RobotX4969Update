package org.firstinspires.ftc.teamcode.core.util;

/**
 * A utility class for handling timing operations in FTC robotics programs.
 * <p>
 * Supports both standard stopwatch functionality (tracking elapsed time)
 * and countdown timer functionality with millisecond precision.
 * </p>
 *
 * @author John Daniher
 * @author Gavin Farrell
 * @version 2.0
 */
public final class Clock {

    /**
     * Defines the operational mode of the clock.
     */
    private enum Mode {
        /** Clock acts as a stopwatch, tracking increasing elapsed time. */
        STOPWATCH,
        /** Clock acts as a timer, counting down from a specified duration. */
        TIMER
    }

    /**
     * Represents the current state of the clock's execution.
     */
    private enum ClockState {
        /** Clock is actively tracking time. */
        RUNNING,
        /** Clock is paused and maintaining its accumulated time without increasing. */
        PAUSED,
        /** Clock is stopped and inactive. */
        STOPPED
    }

    /** The operational mode (STOPWATCH or TIMER) of this clock instance. */
    private final Mode mode;

    /** The total target duration for a timer in nanoseconds (0 if configured as a stopwatch). */
    private final long targetDuration;

    /** The current state (RUNNING, PAUSED, or STOPPED) of this clock instance. */
    private ClockState state;

    /** The system timestamp in nanoseconds (via System.nanoTime()) when the current running segment started. */
    private long startTime;

    /** The total accumulated time in nanoseconds across prior running segments before the current pause or stop. */
    private long accumulatedTime;

    /**
     * Private constructor to instantiate a Clock in either Stopwatch or Timer mode.
     *
     * @param mode     The mode of the clock (TIMER or STOPWATCH).
     * @param duration The target duration in milliseconds (used only if mode is TIMER).
     */
    private Clock(Mode mode, long duration) {
        this.mode = mode;
        this.targetDuration = duration * 1_000_000;
        this.state = ClockState.STOPPED;
    }

    /**
     * Creates a new Clock instance configured as a countdown timer.
     *
     * @param duration The target countdown duration in milliseconds.
     * @return A new {@link Clock} instance operating as a timer.
     */
    public static Clock asTimer(long duration) {
        return new Clock(Mode.TIMER, duration);
    }

    /**
     * Creates a new Clock instance configured as an open-ended stopwatch.
     *
     * @return A new {@link Clock} instance operating as a stopwatch.
     */
    public static Clock asStopwatch() {
        return new Clock(Mode.STOPWATCH, 0);
    }

    /**
     * Starts the clock if it is currently in the stopped state.
     * Has no effect if the clock is already running or paused.
     */
    public void start() {
        if (state == ClockState.STOPPED) {
            startTime = System.nanoTime();
            accumulatedTime = 0;
            state = ClockState.RUNNING;
        }
    }

    /**
     * Pauses the clock if it is currently running, locking the elapsed time.
     * Has no effect if the clock is already stopped or paused.
     */
    public void pause() {
        long elapsedTime = getElapsedNanoTime();

        if (state == ClockState.RUNNING) {
            state = ClockState.PAUSED;
            accumulatedTime = elapsedTime;
        }
    }

    /**
     * Resumes the clock if it is currently paused, continuing time tracking from where it left off.
     * Has no effect if the clock is running or stopped.
     */
    public void resume() {
        if (state == ClockState.PAUSED) {
            state = ClockState.RUNNING;
            startTime = System.nanoTime();
        }
    }

    /**
     * Resets the clock and returns it to the stopped state.
     * Clears all recorded and accumulated time.
     */
    public void reset() {
        startTime = System.nanoTime();
        accumulatedTime = 0;
        state = ClockState.STOPPED;
    }

    /**
     * Gets the total time that has elapsed since the clock was started, excluding paused duration.
     *
     * @return The elapsed time in nanoseconds.
     */
    public long getElapsedNanoTime() {
        if (state == ClockState.RUNNING) {
            return (System.nanoTime() - startTime) + accumulatedTime;
        }

        return accumulatedTime;
    }

    /**
     * Gets the total time that has elapsed since the clock was started, excluding paused duration.
     *
     * @return The elapsed time in milliseconds.
     */
    public long getElapsedMilliTime() {
        return getElapsedNanoTime() / 1_000_000;
    }

    public long getElapsedSecondsTime() {return getElapsedMilliTime() / 1_000;}

    /**
     * Gets the remaining time on a timer.
     *
     * @return The remaining duration in milliseconds, or 0 if time has expired.
     *         Returns 0 if called on a stopwatch mode clock.
     */
    public long getRemainingTime() {
        if (mode != Mode.TIMER) {
            return 0;
        }

        return Math.max(0, (targetDuration - getElapsedNanoTime()) / 1_000_000);
    }

    /**
     * Checks whether a timer has finished its countdown.
     *
     * @return {@code true} if the remaining timer duration is 0, {@code false} otherwise.
     */
    public boolean isTimerDone() {
        if (mode != Mode.TIMER) {
            return false;
        }

        return getRemainingTime() == 0;
    }

}