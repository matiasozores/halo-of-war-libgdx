package com.haloofwar.events;

public class AttackEvent {
    private final boolean pressed;

    public AttackEvent(boolean pressed) {
        this.pressed = pressed;
    }

    public boolean isPressed() {
        return this.pressed;
    }
}
