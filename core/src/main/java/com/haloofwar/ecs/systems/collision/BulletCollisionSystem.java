package com.haloofwar.ecs.systems.collision;

import com.haloofwar.ecs.Entity;
import com.haloofwar.ecs.components.gameplay.BulletComponent;
import com.haloofwar.ecs.events.CollisionEvent;
import com.haloofwar.ecs.events.EventBus;
import com.haloofwar.ecs.events.types.BulletHitEvent;
import com.haloofwar.ecs.systems.ECSSystem;

public class BulletCollisionSystem implements ECSSystem {

    private final EventBus bus;

    public BulletCollisionSystem(EventBus bus) {
        this.bus = bus;
        this.bus.subscribe(CollisionEvent.class, this::onCollision);
    }
    
    private void onCollision(CollisionEvent event) {
        // Revisar cuál es la bala
        Entity bulletEntity = null;
        Entity targetEntity = null;

        if(event.a.hasComponent(BulletComponent.class)) {
            bulletEntity = event.a;
            targetEntity = event.b;
        } else if(event.b.hasComponent(BulletComponent.class)) {
            bulletEntity = event.b;
            targetEntity = event.a;
        }

        if(bulletEntity == null) return; // ninguna bala involucrada

        // Desactivar la bala
        bulletEntity.getComponent(BulletComponent.class).active = false;

        // Publicar evento solo para la bala y el target real
        this.bus.publish(new BulletHitEvent(bulletEntity, targetEntity));
    }

}
