package com.haloofwar.ecs.systems.physics;

import com.haloofwar.ecs.Entity;
import com.haloofwar.ecs.components.physics.MovementComponent;
import com.haloofwar.ecs.components.physics.TransformComponent;
import com.haloofwar.ecs.systems.BaseSystem;

public class MovementSystem extends BaseSystem {

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

            if (movement == null || transform == null) continue;

            float dirX = movement.controller.getDirectionX();
            float dirY = movement.controller.getDirectionY();

            // Guardar la posición anterior
            movement.lastX = transform.x;
            movement.lastY = transform.y;

            // Mover
            transform.x += dirX * movement.speed * delta;
            transform.y += dirY * movement.speed * delta;
        }
    }

}
