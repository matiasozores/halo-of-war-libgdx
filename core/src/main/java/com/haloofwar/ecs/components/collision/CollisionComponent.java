package com.haloofwar.ecs.components.collision;

import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.ecs.components.Component;
import com.haloofwar.ecs.components.physics.TransformComponent;

public class CollisionComponent implements Component {
    public final Rectangle bounds;
    public boolean active = true;

    public CollisionComponent(float width, float height) {
        this.bounds = new Rectangle(0, 0, width, height);
    }

    public void syncWithTransform(TransformComponent transform) {
        this.bounds.setPosition(transform.x, transform.y);
    }
}

