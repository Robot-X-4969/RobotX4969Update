package org.firstinspires.ftc.teamcode.core.util;

import org.firstinspires.ftc.teamcode.core.templates.BaseOpMode;

import java.util.HashMap;
import java.util.Iterator;

public final class Scheduler {

    private final HashMap<String, Event> eventMap = new HashMap<>();
    private final BaseOpMode opMode;

    public Scheduler(BaseOpMode opMode){

        this.opMode = opMode;

    }

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

            opMode.logData(new LogEntry(BaseOpMode.EntryType.ERROR, "[ERROR] in " + Scheduler.class.getName() + ": failed to schedule event " + id + " because it already exists", null));

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

            opMode.logData(new LogEntry(BaseOpMode.EntryType.ERROR, "[ERROR] in " + Scheduler.class.getName() + ": failed to cancel event " + id + " because it does not exist", null));

        }

    }

}