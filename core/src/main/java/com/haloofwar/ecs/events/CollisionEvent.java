package com.haloofwar.ecs.events;

import com.haloofwar.ecs.Entity;

public class CollisionEvent {
    public final Entity a;
    public final Entity b;

    public CollisionEvent(Entity a, Entity b) {
        this.a = a;
        this.b = b;
    }
}
