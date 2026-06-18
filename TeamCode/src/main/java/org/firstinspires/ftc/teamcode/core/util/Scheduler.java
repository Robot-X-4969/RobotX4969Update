package org.firstinspires.ftc.teamcode.core.util;

import java.util.HashMap;
import java.util.Iterator;

public class Scheduler {

    private final HashMap<String, Event> eventMap = new HashMap<>();

    public void loop(){

        Iterator<Event> iterator = eventMap.values().iterator();

        while(iterator.hasNext()){

            Event event = iterator.next();

            if(event.getStopwatch().isTimerDone() && event.isRepeating()){

                event.run();
                event.getStopwatch().reset();

            } else if(event.getStopwatch().isTimerDone() && !event.isRepeating()) {

                event.run();
                iterator.remove();

            }

        }

    }

    public void scheduleEvent(String id, long millis, Runnable action, boolean repeating){

        if(getEvent(id) == null) {

            eventMap.put(id, new Event(id, millis, action, repeating));

        }

    }

    public void rescheduleEvent(String id, Long newMillis, Runnable newAction, Boolean newRepeating) {

        Event existing = getEvent(id);

        if(existing != null) {

            cancelEvent(id);

            long millis = newMillis == null ? (existing.getStopwatch().getRemainingNanoTime() / 1_000_000) : newMillis;
            Runnable action = newAction == null ? existing.getAction() : newAction;
            boolean repeating = newRepeating == null ? existing.isRepeating() : newRepeating;
            eventMap.put(id, new Event(id, millis, action, repeating));

        }

    }

    public Event getEvent(String id) {

        return eventMap.get(id);

    }

    public void cancelEvent(String id) {

        eventMap.remove(id);

    }

}
