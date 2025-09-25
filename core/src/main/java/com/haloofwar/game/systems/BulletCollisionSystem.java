package com.haloofwar.game.systems;

import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.CollisionEvent;
import com.haloofwar.engine.events.DamageEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.RemoveEntityEvent;
import com.haloofwar.engine.interfaces.Registrable;
import com.haloofwar.game.components.BulletComponent;
import com.haloofwar.game.components.HealthComponent;

public class BulletCollisionSystem implements Registrable {

    private final EventBus bus;

    public BulletCollisionSystem(EventBus bus) {
        this.bus = bus;
        this.bus.subscribe(CollisionEvent.class, this::onCollision);
    }
    
    private void onCollision(CollisionEvent event) {
        Entity bulletEntity = null;
        Entity targetEntity = null;
        
        if (event.a.hasComponent(BulletComponent.class)) {
            bulletEntity = event.a;
            targetEntity = event.b;
        } else if (event.b.hasComponent(BulletComponent.class)) {
            bulletEntity = event.b;
            targetEntity = event.a;
        }

        if (bulletEntity == null) {
        	return;
        }

        BulletComponent bulletComp = bulletEntity.getComponent(BulletComponent.class);
        bulletComp.active = false;

        this.bus.publish(new RemoveEntityEvent(bulletEntity));

        if (targetEntity.hasComponent(HealthComponent.class)) {
            this.bus.publish(new DamageEvent(targetEntity, bulletComp.damage, bulletEntity)); 
        }
    }

    @Override
    public void register(Entity entity) { }

    @Override
    public void unregister(Entity entity) { }
}
