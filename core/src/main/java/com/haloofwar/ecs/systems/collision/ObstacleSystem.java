package com.haloofwar.ecs.systems.collision;

import com.haloofwar.ecs.Entity;
import com.haloofwar.ecs.components.collision.ObstacleComponent;
import com.haloofwar.ecs.components.physics.MovementComponent;
import com.haloofwar.ecs.events.EventBus;
import com.haloofwar.ecs.events.types.CollisionEvent;
import com.haloofwar.ecs.systems.EntitySystemInterface;

public class ObstacleSystem implements EntitySystemInterface {

    public ObstacleSystem(EventBus bus) {
        bus.subscribe(CollisionEvent.class, this::onCollision);
    }

    private void onCollision(CollisionEvent event) {
        Entity entityA = event.a;
        Entity entityB = event.b;

        if (entityA.hasComponent(MovementComponent.class) && entityB.hasComponent(ObstacleComponent.class)) {
            this.blockMovement(entityA, entityB);
        } else if (entityB.hasComponent(MovementComponent.class) && entityA.hasComponent(ObstacleComponent.class)) {
            this.blockMovement(entityB, entityA);
        }
    }
  
    private void blockMovement(Entity movingEntity, Entity obstacleEntity) {
    	System.out.println("Bloqueando movimiento");
    }

}
