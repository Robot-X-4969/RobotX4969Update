package org.firstinspires.ftc.teamcode.core.util;

/**
 * Represents a single timed task within the scheduling ecosystem.
 * <p>
 * An <code>Event</code> wraps an asynchronous action (a {@link Runnable}) alongside
 * a high-performance countdown timer. It supports both <b>one-shot execution</b>
 * and <b>continuous execution loops</b>, and features a safe "soft-delete"
 * cancellation state to prevent conflicts during runtime modifications.
 * </p>
 *
 * @author John Daniher (original implementation)
 * @author Gavin Farrell (refactor)
 * @version 2.0
 * @see Scheduler
 * @see Clock
 */
public final class Event {

    /**The unique identifier for this event.*/
    private final String id;
    /**The action to be executed when this event is run.*/
    private final Runnable action;
    /**The timer used to track the timing of this event.*/
    private final Clock timer;
    /**Indicates whether this event should repeat after execution.*/
    private boolean repeating;
    /**Indicates whether this event has been canceled and should not be executed.*/
    private boolean cancelled;

    /**
     * Constructs a new Event with the specified parameters.
     *
     * @param id the unique identifier for this event
     * @param millis the delay in milliseconds before this event is executed
     * @param action the action to be executed when this event is run
     * @param repeating true if the event should repeat after execution, false otherwise
     */
    public Event(String id, long millis, Runnable action, boolean repeating){

        this.id = id;
        this.action = action;
        this.timer = Clock.asTimer(millis);
        this.repeating = repeating;

    }

    /**
     * Runs the action associated with this event.
     */
    public void run(){

        action.run();

    }

    /**
     * Returns the timer used to track the event's timing.
     *
     * @return the timer associated with this event
     */
    public Clock getTimer() {

        return timer;

    }

    /**
     * Returns whether this event is set to repeat after execution.
     *
     * @return the repeating status of this event
     */
    public boolean isRepeating() {

        return repeating;

    }

    /**
     * Sets whether this event should repeat after execution.
     *
     * @param repeating true if the event should repeat, false otherwise
     */
    public void repeat(boolean repeating) {

        this.repeating = repeating;

    }

    /**
     * Returns whether this event has been canceled.
     *
     * @return true if the event is canceled, false otherwise
     */
    public boolean isCancelled() {

        return cancelled;

    }

    /**
     * Cancels this event, preventing it from being executed.
     */
    public void cancel() {

        cancelled = true;

    }

}
