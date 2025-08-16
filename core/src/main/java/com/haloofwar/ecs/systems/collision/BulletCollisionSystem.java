package com.haloofwar.ecs.systems.collision;

import com.haloofwar.ecs.Entity;
import com.haloofwar.ecs.components.gameplay.BulletComponent;
import com.haloofwar.ecs.components.gameplay.HealthComponent;
import com.haloofwar.ecs.events.EventBus;
import com.haloofwar.ecs.events.types.BulletHitEvent;
import com.haloofwar.ecs.events.types.CollisionEvent;
import com.haloofwar.ecs.events.types.DamageEvent;
import com.haloofwar.ecs.systems.EntitySystemInterface;

public class BulletCollisionSystem implements EntitySystemInterface {

    private final EventBus bus;

    public BulletCollisionSystem(EventBus bus) {
        this.bus = bus;
        this.bus.subscribe(CollisionEvent.class, this::onCollision);
    }
    
    private void onCollision(CollisionEvent event) {
        Entity bulletEntity = null;
        Entity targetEntity = null;
        
        if(event.a.hasComponent(BulletComponent.class)) {
            bulletEntity = event.a;
            targetEntity = event.b;
        } else if(event.b.hasComponent(BulletComponent.class)) {
            bulletEntity = event.b;
            targetEntity = event.a;
        }

        if(bulletEntity == null) {
        	return;
        }
        
        final int DAMAGE = bulletEntity.getComponent(BulletComponent.class).damage;
        
        bulletEntity.getComponent(BulletComponent.class).active = false;
        this.bus.publish(new BulletHitEvent(bulletEntity, targetEntity));
        
        if (targetEntity.hasComponent(HealthComponent.class)) {
            this.bus.publish(new DamageEvent(targetEntity, DAMAGE, bulletEntity)); 
        }
    }

}
