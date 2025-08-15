package com.haloofwar.ecs.systems.render;

import com.haloofwar.ecs.Entity;
import com.haloofwar.ecs.components.physics.MovementComponent;
import com.haloofwar.ecs.components.render.AnimationComponent;
import com.haloofwar.ecs.systems.BaseSystem;

public class AnimationSystem extends BaseSystem {

    @Override
    public void register(Entity e) {
        if (e.hasComponent(AnimationComponent.class)) {
            super.register(e);
        }
    }

    @Override
    public void update(float delta) {
        for (Entity entity : this.entities) {
            AnimationComponent animation = entity.getComponent(AnimationComponent.class);
            if (animation == null) continue;

            MovementComponent movement = entity.getComponent(MovementComponent.class);
            float dirX = 0, dirY = 0;
            if (movement != null) {
                dirX = movement.controller.getDirectionX();
                dirY = movement.controller.getDirectionY();
            }

            animation.update(delta, dirX, dirY);
        }
    }
}
