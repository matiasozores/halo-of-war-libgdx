package com.haloofwar.events;

import com.haloofwar.components.Entity;

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
