package com.haloofwar.engine.events.online;

import com.haloofwar.common.enumerators.Direction;

public class MoveEventOnline {
    public final int IDENTIFIER;
    public Direction direction;
    public boolean pressed;

    public MoveEventOnline(int IDENTIFIER, Direction direction, boolean pressed){
        this.IDENTIFIER = IDENTIFIER;
        this.direction = direction;
        this.pressed = pressed;
    }
}
