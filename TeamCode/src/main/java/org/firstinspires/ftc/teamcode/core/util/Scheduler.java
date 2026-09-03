package org.firstinspires.ftc.teamcode.core.util;

import org.firstinspires.ftc.teamcode.core.core.BaseOpMode;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Manages asynchronous timed events and scheduled tasks for FTC OpModes.
 * <p>
 * Handles registering, polling, executing, and cancelling single-shot
 * or recurring {@link Event} instances keyed by unique String identifiers.
 * </p>
 *
 * @author John Daniher
 * @author Gavin Farrell (update)
 * @version 1.0
 */
public final class Scheduler {

    /** Map storing active scheduled events indexed by their unique String IDs. */
    private final HashMap<String, Event> eventMap;

    /** The active OpMode instance used for logging system errors. */
    private final BaseOpMode opMode;

    /**
     * Constructs a new Scheduler bound to an OpMode.
     *
     * @param opMode The active {@link BaseOpMode} instance.
     */
    public Scheduler(BaseOpMode opMode) {
        this.eventMap = new HashMap<>();
        this.opMode = opMode;
    }

    /**
     * Iterates through all registered events, updating timers and executing completed tasks.
     * <p>
     * Must be called inside the main OpMode update loop. Automatically removes cancelled
     * or non-repeating finished events, and resets timers for repeating events.
     * </p>
     */
    public void pollEvents() {
        Iterator<Event> iterator = eventMap.values().iterator();

        while (iterator.hasNext()) {
            Event event = iterator.next();

            if (event.isCancelled()) {
                iterator.remove();
                continue;
            }

            if (!event.getTimer().isTimerDone()) {
                continue;
            }

            event.run();

            if (event.isRepeating()) {
                event.getTimer().reset();
                event.getTimer().start();
            } else {
                iterator.remove();
            }
        }
    }

    /**
     * Schedules a new timed event with optional auto-repeat functionality.
     *
     * @param id        A unique string identifier for the event.
     * @param millis    The duration in milliseconds before the event triggers.
     * @param action    The {@link Runnable} task to execute upon trigger.
     * @param repeating {@code true} to re-trigger the event continuously; {@code false} for single execution.
     */
    public void scheduleEvent(String id, long millis, Runnable action, boolean repeating) {
        if (eventMap.get(id) == null) {
            eventMap.put(id, new Event(id, millis, action, repeating));
        } else {
            opMode.logData(new LogEntry(LogEntry.EntryType.ERROR, "[ERROR] in " + Scheduler.class.getName() + ": failed to schedule event " + id + " because it already exists", null));
        }
    }

    /**
     * Schedules a single-shot timed event that executes once.
     *
     * @param id     A unique string identifier for the event.
     * @param millis The duration in milliseconds before the event triggers.
     * @param action The {@link Runnable} task to execute upon trigger.
     */
    public void scheduleEvent(String id, long millis, Runnable action) {
        scheduleEvent(id, millis, action, false);
    }

    /**
     * Cancels an active scheduled event by its identifier.
     * <p>
     * Logs an error message if no event exists with the provided ID.
     * </p>
     *
     * @param id The unique string identifier of the event to cancel.
     */
    public void cancelEvent(String id) {
        Event event = eventMap.get(id);

        if (event != null) {
            event.cancel();
        } else {
            opMode.logData(new LogEntry(LogEntry.EntryType.ERROR, "[ERROR] in " + Scheduler.class.getName() + ": failed to cancel event " + id + " because it does not exist", null));
        }
    }
}