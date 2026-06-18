package org.firstinspires.ftc.teamcode.core.util;

public class Event {

    private final String id;
    private final Runnable action;
    private final Stopwatch stopwatch;
    private boolean repeating;

    public Event(String id, long millis, Runnable action, boolean repeating){

        this.id = id;
        this.action = action;
        this.stopwatch = new Stopwatch(millis);
        this.repeating = repeating;

    }

    public void run(){

        action.run();

    }

    public Stopwatch getStopwatch(){

        return stopwatch;

    }

    public Runnable getAction() {

        return action;

    }

    public boolean isRepeating() {

        return repeating;

    }

    public void setRepeating(boolean repeating) {

        this.repeating = repeating;

    }

}
