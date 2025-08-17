package com.haloofwar.ecs.events.types.collision;

import com.haloofwar.ecs.Entity;

public class CollisionEvent {
    public final Entity a, b;

    public CollisionEvent(Entity a, Entity b) {
        this.a = a;
        this.b = b;
    }
}

