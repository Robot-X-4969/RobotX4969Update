package org.firstinspires.ftc.teamcode.core.util;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Manages the registration, timing, execution, and safe removal of {@link Event} instances.
 * <p>
 * The <code>Scheduler</code> serves as a central hub for handling asynchronous-like
 * tasks synchronously within a single-threaded robot loop. It relies on a <code>HashMap</code>
 * to store events by unique string identifiers, ensuring fast lookups and preventing
 * duplicate event registrations.
 * </p>
 *
 * @author John Daniher (original implementation)
 * @author Gavin Farrell (refactor)
 * @version 2.0
 * @see Event
 */
public final class Scheduler {


    private final HashMap<String, Event> eventMap = new HashMap<>();

    /**
     * Polls all events in the event map and runs them if their timer is done.
     */
    public void pollEvents(){

        Iterator<Event> iterator = eventMap.values().iterator();

        while(iterator.hasNext()){

            Event event = iterator.next();

            if(event.isCancelled()){

                iterator.remove();
                continue;

            }
            if(!event.getTimer().isTimerDone()){

                continue;

            }

            event.run();

            if(event.isRepeating()){

                event.getTimer().reset();
                event.getTimer().start();

            } else {

                iterator.remove();

            }

        }

    }

    /**
     * Schedules an event to be run after a certain amount of time.
     *
     * @param id The unique id of the event.
     * @param millis The time in milliseconds after which the event should be run.
     * @param action The action to be run.
     * @param repeating Whether the event should be repeated or not.
     */
    public void scheduleEvent(String id, long millis, Runnable action, boolean repeating){

        if(eventMap.get(id) == null) {

            eventMap.put(id, new Event(id, millis, action, repeating));

        } else {

            //throw and error that the event already exists

        }

    }

    /**
     * Overloaded function that schedules a non-repeating event to be run after a certain amount of time.
     *
     * @param id The unique id of the event.
     * @param millis The time in milliseconds after which the event should be run.
     * @param action The action to be run.
     */
    public void scheduleEvent(String id, long millis, Runnable action) {

        scheduleEvent(id, millis, action, false);

    }

    /**
     * Cancels an event with the given id.
     *
     * @param id The unique id of the event to be canceled.
     */
    public void cancelEvent(String id) {

        Event event = eventMap.get(id);

        if(event != null){

            event.cancel();

        } else {

            //throw an error that the event does not exist

        }

    }

}
