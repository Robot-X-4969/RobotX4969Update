package org.firstinspires.ftc.teamcode.core.util;

/**
 * Represents a scheduled task or trigger within the robotics framework.
 * <p>
 * Combines an action ({@link Runnable}) with a countdown timer ({@link Clock})
 * to manage single-execution or repeating scheduled events.
 * </p>
 *
 * @author John Daniher
 * @author Gavin Farrell (update)
 * @version 2.0
 */
public final class Event {

    /** Unique identifier for this event instance. */
    private final String id;

    /** The task to be executed when the event triggers. */
    private final Runnable action;

    /** The countdown timer tracking the delay or interval before execution. */
    private final Clock timer;

    /** Indicates whether the event should automatically reschedule and run repeatedly. */
    private boolean repeating;

    /** Indicates whether the event has been explicitly cancelled to prevent execution. */
    private boolean cancelled;

    /**
     * Constructs a new Event with a specified ID, delay, action, and repeat behavior.
     *
     * @param id        A unique string identifier for this event.
     * @param millis    The duration in milliseconds before the event triggers.
     * @param action    The {@link Runnable} task to execute when triggered.
     * @param repeating {@code true} if the event should automatically repeat; {@code false} for single execution.
     */
    public Event(String id, long millis, Runnable action, boolean repeating) {
        this.id = id;
        this.action = action;
        this.timer = Clock.asTimer(millis);
        this.repeating = repeating;
    }

    /**
     * Executes the assigned {@link Runnable} action for this event.
     */
    public void run() {
        action.run();
    }

    /**
     * Gets the countdown timer associated with this event.
     *
     * @return The {@link Clock} instance handling timing for this event.
     */
    public Clock getTimer() {
        return timer;
    }

    /**
     * Checks whether this event is set to repeat upon completion.
     *
     * @return {@code true} if the event is repeating, {@code false} otherwise.
     */
    public boolean isRepeating() {
        return repeating;
    }

    /**
     * Sets whether this event should repeat continuously.
     *
     * @param repeating {@code true} to make the event repeat, {@code false} to set it to run once.
     */
    public void repeat(boolean repeating) {
        this.repeating = repeating;
    }

    /**
     * Checks whether this event has been cancelled.
     *
     * @return {@code true} if cancelled, {@code false} otherwise.
     */
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Cancels the event, preventing any future execution by event handlers or schedulers.
     */
    public void cancel() {
        cancelled = true;
    }
}