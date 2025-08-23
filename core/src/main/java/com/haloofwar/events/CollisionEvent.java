package com.haloofwar.events;

import com.haloofwar.components.Entity;

public class CollisionEvent {
    public final Entity a, b;

    public CollisionEvent(Entity a, Entity b) {
        this.a = a;
        this.b = b;
    }
}

