package com.haloofwar.engine.events;

public class InteractEvent {
    private final boolean pressed;
    
    public InteractEvent(boolean pressed) {
        this.pressed = pressed;
    }

    public boolean isPressed() {
        return this.pressed;
    }
}
