package org.firstinspires.ftc.teamcode.core.util;

public class Button {

    private boolean lastState;
    private boolean currentState;
    private long timePressed;

    public void update(boolean newState) {

        if(newState != currentState && newState){

            timePressed = System.currentTimeMillis();

        }

        lastState = currentState;
        currentState = newState;

    }

    public boolean currentState() {

        return currentState;

    }

    public boolean isReleased() {

        return !currentState;

    }

    public boolean justPressed() {

        return currentState && !lastState;

    }

    public boolean justReleased(){

        return lastState && !currentState;

    }

    public long getPressedDuration(){

        if(currentState){

            return System.currentTimeMillis() - timePressed;

        }

        return 0;

    }

}
