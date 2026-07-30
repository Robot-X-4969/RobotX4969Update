package org.firstinspires.ftc.teamcode.core.util;

public final class GamepadButton {

    private final Clock clock;
    private boolean lastState;
    private boolean currentState;
    private long timePressed;

    public GamepadButton() {

        clock = Clock.asStopwatch();

    }

    public void update(boolean newState) {

        if(newState != currentState && newState){

            clock.start();

        } else if(newState != currentState && !newState){

            timePressed = clock.getElapsedNanoTime();
            clock.reset();

        }

        lastState = currentState;
        currentState = newState;

    }

    public boolean isPressed() {

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
