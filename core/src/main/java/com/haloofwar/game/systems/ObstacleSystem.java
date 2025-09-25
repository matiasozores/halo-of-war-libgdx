package com.haloofwar.game.systems;

import com.haloofwar.engine.components.TransformComponent;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.CollisionEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.interfaces.Registrable;
import com.haloofwar.game.components.MovementComponent;
import com.haloofwar.game.components.ObstacleComponent;
import com.haloofwar.game.components.PlayerComponent;

public class ObstacleSystem implements Registrable {

    public ObstacleSystem(EventBus bus) {
        bus.subscribe(CollisionEvent.class, this::onCollision);
    }

    private void onCollision(CollisionEvent event) {
        Entity entityA = event.a;
        Entity entityB = event.b;

        if(entityA.hasComponent(PlayerComponent.class)) {
        } else if(entityB.hasComponent(PlayerComponent.class) && entityA.hasComponent(ObstacleComponent.class)) {
        	this.blockMovement(entityB, entityA);
        }
        
        if (entityA.hasComponent(MovementComponent.class) && entityB.hasComponent(ObstacleComponent.class)) {
        	this.blockMovement(entityA, entityB);
        } else if (entityB.hasComponent(MovementComponent.class) && entityA.hasComponent(ObstacleComponent.class)) {
        	this.blockMovement(entityB, entityA);
        }
    }

    private void blockMovement(Entity movingEntity, Entity obstacleEntity) {
        TransformComponent transform = movingEntity.getComponent(TransformComponent.class);
        MovementComponent movement = movingEntity.getComponent(MovementComponent.class);

        if (transform == null || movement == null) {
        	return;
        }

        float originalX = transform.x;
        float originalY = transform.y;

        transform.x = originalX;
        transform.y = movement.lastY;
        if (this.collides(movingEntity, obstacleEntity)) {
            transform.x = movement.lastX;
        }

        transform.x = transform.x;
        transform.y = originalY;
        if (this.collides(movingEntity, obstacleEntity)) {
            transform.y = movement.lastY;
        }
    }

    
    private boolean collides(Entity a, Entity b) {
        TransformComponent transformA = a.getComponent(TransformComponent.class);
        TransformComponent transformB = b.getComponent(TransformComponent.class);

        if (transformA == null || transformB == null) {
            return false; // si alguno no tiene transform, no puede colisionar
        }

        boolean overlapX = transformA.x < transformB.x + transformB.width && transformA.x + transformA.width > transformB.x;
        boolean overlapY = transformA.y < transformB.y + transformB.height && transformA.y + transformA.height > transformB.y;

        return overlapX && overlapY;
    }

	@Override
	public void register(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregister(Entity entity) {
		// TODO Auto-generated method stub
		
	}


}
