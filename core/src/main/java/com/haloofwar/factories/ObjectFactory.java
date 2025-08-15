package com.haloofwar.factories;

import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.dependences.assets.TextureManager;
import com.haloofwar.ecs.Entity;
import com.haloofwar.enumerators.entities.objects.ObjectType;
import com.haloofwar.factories.components.ComponentPresets;
import com.haloofwar.factories.components.EntityBuilder;

public class ObjectFactory {

	private static final float WIDTH = 16, HEIGHT = 16;
	
    public static Entity createItem(Rectangle rect, ObjectType type, TextureManager manager) {
        return new EntityBuilder<>()
                .withComponent(ComponentPresets.defaultTransform(rect.x, rect.y, WIDTH, HEIGHT))
                .withComponent(ComponentPresets.defaultCollision(rect.x, rect.y, rect.width, rect.height))
                .withComponent(ComponentPresets.defaultName(type))
                .withComponent(ComponentPresets.defaultRender(type, manager))
                .withComponent(ComponentPresets.defaultPickup())
                .build();
    }
}
