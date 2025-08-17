package com.haloofwar.ecs.events.types.gameplay;

import com.haloofwar.ecs.Entity;

public class DamageEvent {
    public final Entity target;
    public final int amount;
    public final Entity source;

    public DamageEvent(Entity target, int amount, Entity source) {
        this.target = target;
        this.amount = amount;
        this.source = source;
    }
}
