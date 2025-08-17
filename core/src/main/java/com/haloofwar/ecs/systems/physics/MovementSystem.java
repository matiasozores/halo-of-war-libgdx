package com.haloofwar.ecs.systems.physics;

import com.haloofwar.ecs.Entity;
import com.haloofwar.ecs.components.physics.MovementComponent;
import com.haloofwar.ecs.components.physics.TransformComponent;
import com.haloofwar.ecs.systems.BaseSystem;
import com.haloofwar.interfaces.systems.Updatable;

public class MovementSystem extends BaseSystem implements Updatable {

    @Override
    public void register(Entity e) {
        if (e.hasComponent(MovementComponent.class) && e.hasComponent(TransformComponent.class)) {
            super.register(e);
        }
    }

    @Override
    public void update(float delta) {
        for (Entity entity : this.entities) {
            MovementComponent movement = entity.getComponent(MovementComponent.class);
            TransformComponent transform = entity.getComponent(TransformComponent.class);

            if (movement == null || transform == null) {
            	continue;
            }
            
            movement.lastX = transform.x;
            movement.lastY = transform.y;
            
            float dirX = movement.controller.getDirectionX();
            float dirY = movement.controller.getDirectionY();

            // Mover
            transform.x += dirX * movement.speed * delta;
            transform.y += dirY * movement.speed * delta;
        }
    }
}
