package com.haloofwar.factories;

import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.components.Entity;
import com.haloofwar.factories.components.ComponentPresets;
import com.haloofwar.factories.components.EntityBuilder;

public final class ObstacleFactory {

    public static Entity createObstacle(Rectangle rect) {
        return new EntityBuilder<>()
                .withComponent(ComponentPresets.defaultTransform(rect.x, rect.y, rect.width, rect.height))
                .withComponent(ComponentPresets.defaultCollision(rect.width, rect.height))
                .withComponent(ComponentPresets.defaultObstacle())
                .build();
    }
}


