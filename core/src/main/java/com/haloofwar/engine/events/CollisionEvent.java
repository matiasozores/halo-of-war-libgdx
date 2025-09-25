package com.haloofwar.engine.events;

import com.haloofwar.engine.entity.Entity;

public class CollisionEvent {
    public final Entity a, b;

    public CollisionEvent(Entity a, Entity b) {
        this.a = a;
        this.b = b;
    }
}

