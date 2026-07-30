package org.firstinspires.ftc.teamcode.core.util;

public final class Clock {

    private enum Mode {STOPWATCH, TIMER}
    private enum ClockState {RUNNING, PAUSED, STOPPED}

    private final Mode mode;
    private final long targetDuration;
    private ClockState state;
    private long startTime;
    private long accumulatedTime;

    private Clock(Mode mode, long duration) {

        this.mode = mode;
        this.targetDuration = duration * 1_000_000;
        this.state = ClockState.STOPPED;
    }

    public static Clock asTimer(long duration){

        return new Clock(Mode.TIMER, duration);

    }

    public static Clock asStopwatch(){

        return new Clock(Mode.STOPWATCH, 0);

    }

    public void start(){

        if(state == ClockState.STOPPED){

            startTime = System.nanoTime();
            accumulatedTime = 0;
            state = ClockState.RUNNING;

        }

    }

    public void pause(){

        long elapsedTime = getElapsedNanoTime();

        if(state == ClockState.RUNNING){
            state = ClockState.PAUSED;
            accumulatedTime = elapsedTime;
        }

    }

    public void resume(){

        if(state == ClockState.PAUSED) {
            state = ClockState.RUNNING;
            startTime = System.nanoTime();
        }

    }

    public void reset(){

        startTime = System.nanoTime();
        accumulatedTime = 0;
        state = ClockState.STOPPED;

    }

    public long getElapsedNanoTime(){

        if(state == ClockState.RUNNING){
            return (System.nanoTime() - startTime) + accumulatedTime;
        }

        return accumulatedTime;

    }

    public long getElapsedMilliTime(){

        return getElapsedNanoTime() / 1_000_000;

    }

    public long getRemainingTime(){

        if(mode != Mode.TIMER){

        }

        return Math.max(0, (targetDuration - getElapsedNanoTime()) / 1_000_000);

    }

    public boolean isTimerDone(){

        if(mode != Mode.TIMER){

        }

        return getRemainingTime() == 0;

    }

}