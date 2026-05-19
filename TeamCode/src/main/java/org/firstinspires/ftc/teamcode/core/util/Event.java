package org.firstinspires.ftc.teamcode.core.util;

public class Event {

    private final String id;
    private final Runnable action;
    private final Stopwatch stopwatch;

    public Event(String id, long millis, Runnable action){

        this.id = id;
        this.action = action;
        this.stopwatch = new Stopwatch(millis);

    }

    public void run(){

        action.run();

    }

    public Stopwatch getStopwatch(){

        return this.stopwatch;

    }

    public Runnable getAction() {

        return this.action;

    }

}
