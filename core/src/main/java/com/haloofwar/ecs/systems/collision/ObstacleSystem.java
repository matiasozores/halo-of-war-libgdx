package com.haloofwar.ecs.systems.collision;

import com.haloofwar.ecs.Entity;
import com.haloofwar.ecs.components.collision.ObstacleComponent;
import com.haloofwar.ecs.components.physics.MovementComponent;
import com.haloofwar.ecs.components.physics.TransformComponent;
import com.haloofwar.ecs.events.CollisionEvent;
import com.haloofwar.ecs.events.EventBus;
import com.haloofwar.ecs.systems.ECSSystem;

public class ObstacleSystem implements ECSSystem {

    public ObstacleSystem(EventBus bus) {
        bus.subscribe(CollisionEvent.class, this::onCollision);
    }

    private void onCollision(CollisionEvent event) {
        Entity entityA = event.a;
        Entity entityB = event.b;

        // Case 1: A is moving, B is an obstacle
        if (entityA.hasComponent(MovementComponent.class) && entityB.hasComponent(ObstacleComponent.class)) {
            this.blockMovement(entityA, entityB);
        }

        // Case 2: B is moving, A is an obstacle
        else if (entityB.hasComponent(MovementComponent.class) && entityA.hasComponent(ObstacleComponent.class)) {
            this.blockMovement(entityB, entityA);
        }
    }

    private void blockMovement(Entity movingEntity, Entity obstacleEntity) {
        MovementComponent movement = movingEntity.getComponent(MovementComponent.class);
        TransformComponent transform = movingEntity.getComponent(TransformComponent.class);
        TransformComponent obstacleTransform = obstacleEntity.getComponent(TransformComponent.class);

        if (movement == null || transform == null || obstacleTransform == null) return;

        // Save current position
        float currentX = transform.x;
        float currentY = transform.y;

        float lastX = movement.lastX;
        float lastY = movement.lastY;

        // Calculate delta
        float deltaX = currentX - lastX;
        float deltaY = currentY - lastY;

        // --- Check X movement ---
        if (deltaX != 0) {
            if (deltaX > 0) {
                // Moving right
                if (lastX + transform.hitboxWidth <= obstacleTransform.x &&
                    currentX + transform.hitboxWidth > obstacleTransform.x) {
                    transform.x = obstacleTransform.x - transform.hitboxWidth;
                }
            } else {
                // Moving left
                if (lastX >= obstacleTransform.x + obstacleTransform.hitboxWidth &&
                    currentX < obstacleTransform.x + obstacleTransform.hitboxWidth) {
                    transform.x = obstacleTransform.x + obstacleTransform.hitboxWidth;
                }
            }
        }

        // --- Check Y movement ---
        if (deltaY != 0) {
            if (deltaY > 0) {
                // Moving up
                if (lastY + transform.hitboxHeight <= obstacleTransform.y &&
                    currentY + transform.hitboxHeight > obstacleTransform.y) {
                    transform.y = obstacleTransform.y - transform.hitboxHeight;
                }
            } else {
                // Moving down
                if (lastY >= obstacleTransform.y + obstacleTransform.hitboxHeight &&
                    currentY < obstacleTransform.y + obstacleTransform.hitboxHeight) {
                    transform.y = obstacleTransform.y + obstacleTransform.hitboxHeight;
                }
            }
        }

        // Update last safe position
        movement.lastX = transform.x;
        movement.lastY = transform.y;
    }

}
