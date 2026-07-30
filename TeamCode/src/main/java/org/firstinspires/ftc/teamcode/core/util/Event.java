package org.firstinspires.ftc.teamcode.core.util;

public final class Event {

    private final String id;
    private final Runnable action;
    private final Clock timer;
    private boolean repeating;
    private boolean cancelled;

    public Event(String id, long millis, Runnable action, boolean repeating){

        this.id = id;
        this.action = action;
        this.timer = Clock.asTimer(millis);
        this.repeating = repeating;

    }

    public void run(){

        action.run();

    }

    public Clock getTimer() {

        return timer;

    }

    public boolean isRepeating() {

        return repeating;

    }

    public void repeat(boolean repeating) {

        this.repeating = repeating;

    }

    public boolean isCancelled() {

        return cancelled;

    }

    public void cancel() {

        cancelled = true;

    }

}