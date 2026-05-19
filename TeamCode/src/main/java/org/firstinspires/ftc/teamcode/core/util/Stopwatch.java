package org.firstinspires.ftc.teamcode.core.util;

public class Stopwatch {

    private long startTime;
    private long targetDuration;
    private long pauseTime;
    private boolean isPaused;

    public Stopwatch(long millis){

        start(millis);

    }

    public void reset(){

        startTime = System.nanoTime();

    }

    public void start(long targetMillis){

        reset();

        targetDuration = targetMillis * 1_000_000;

    }

    public void stop(){

        targetDuration = 0;
        isPaused = false;

    }

    public void pause(){

        long remainingTime = getRemainingNanoTime();

        if(remainingTime != 0 && !isPaused){

            isPaused = true;
            pauseTime = remainingTime;

        }

    }

    public void resume(){

        if(isPaused){

            isPaused = false;
            startTime = System.nanoTime();
            targetDuration = pauseTime;

        }

    }

    public long getRemainingNanoTime(){

        if(isPaused){

            return pauseTime;

        }

        long elapsedTime = System.nanoTime() - startTime;
        long remainingTime = targetDuration - elapsedTime;
        return Math.max(0, remainingTime);

    }

    public boolean isTimerDone(){

        return getRemainingNanoTime() == 0;

    }

    public boolean isRunning(){

        return getRemainingNanoTime() > 0 && !isPaused;

    }

}
