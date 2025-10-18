package com.haloofwar.engine.events.online;

import com.haloofwar.common.enumerators.Direction;

public class EntityMoveEventOnline {
    private final Direction direction;
    private final boolean pressed;

    public EntityMoveEventOnline(Direction direction, boolean pressed) {
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
