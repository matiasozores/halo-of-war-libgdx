package com.haloofwar.ecs.events.types.input;

import com.haloofwar.enumerators.events.Direction;

public class MoveEvent {
    private final Direction direction;
    private final boolean pressed;

    public MoveEvent(Direction direction, boolean pressed) {
        this.direction = direction;
        this.pressed = pressed;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public boolean isPressed() {
        return this.pressed;
    }
}
