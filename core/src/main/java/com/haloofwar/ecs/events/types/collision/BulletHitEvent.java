package com.haloofwar.ecs.events.types.collision;

import com.haloofwar.ecs.Entity;

public class BulletHitEvent {
    public final Entity bullet;
    public final Entity target;
    
    public BulletHitEvent(Entity bullet, Entity target) {
        this.bullet = bullet;
        this.target = target;
    }
}
