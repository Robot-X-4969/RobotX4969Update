package org.firstinspires.ftc.teamcode.core.util;

import java.util.HashMap;
import java.util.Iterator;

public final class Scheduler {

    private final HashMap<String, Event> eventMap = new HashMap<>();

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

    public void scheduleEvent(String id, long millis, Runnable action, boolean repeating){

        if(eventMap.get(id) == null) {

            eventMap.put(id, new Event(id, millis, action, repeating));

        } else {

            //throw and error that the event already exists

        }

    }

    public void scheduleEvent(String id, long millis, Runnable action) {

        scheduleEvent(id, millis, action, false);

    }

    public void cancelEvent(String id) {

        Event event = eventMap.get(id);

        if(event != null){

            event.cancel();

        } else {

            //throw an error that the event does not exist

        }

    }

}