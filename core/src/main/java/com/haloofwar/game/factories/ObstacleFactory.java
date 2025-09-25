package com.haloofwar.game.factories;

import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.game.config.ComponentPresets;

public final class ObstacleFactory {

    public static Entity createObstacle(final Rectangle RECTANGLE) {
    	final Entity ENTITY = new Entity();
    	ENTITY.addComponent(ComponentPresets.defaultTransform(RECTANGLE.x, RECTANGLE.y, RECTANGLE.width, RECTANGLE.height));
    	ENTITY.addComponent(ComponentPresets.defaultCollision(RECTANGLE.width, RECTANGLE.height));
    	ENTITY.addComponent(ComponentPresets.defaultObstacle());
    	
        return ENTITY;
    }
}


