package com.haloofwar.engine.events;

import com.haloofwar.common.enumerators.Direction;

public class InventoryScrollEvent {
    public final Direction direction;

    public InventoryScrollEvent(Direction direction) {
        this.direction = direction;
    }
}
