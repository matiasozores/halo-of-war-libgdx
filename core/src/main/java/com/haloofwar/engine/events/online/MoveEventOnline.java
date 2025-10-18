package com.haloofwar.engine.events.online;

import com.haloofwar.common.enumerators.Direction;

public class MoveEventOnline {

    private final int IDENTIFIER;
    private final Direction direction;
    private final boolean pressed;

    public MoveEventOnline(int IDENTIFIER, Direction direction, boolean pressed) {
        this.IDENTIFIER = IDENTIFIER;
        this.direction = direction;
        this.pressed = pressed;
    }

    public int getIDENTIFIER() {
        return IDENTIFIER;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isPressed() {
        return pressed;
    }
}
