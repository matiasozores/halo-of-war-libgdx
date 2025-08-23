package com.haloofwar.events;

public class NextEvent {
    private final boolean pressed;

    public NextEvent(boolean pressed) {
        this.pressed = pressed;
    }

    public boolean isPressed() {
        return this.pressed;
    }
}
