package com.haloofwar.factories;

import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.components.Entity;
import com.haloofwar.dependences.TextureManager;
import com.haloofwar.enumerators.ObjectType;
import com.haloofwar.factories.components.ComponentPresets;
import com.haloofwar.factories.components.EntityBuilder;

public final class ObjectFactory {

	// Por ahora todos los objetos tendran este tamaño, luego se parametrizara 
	private static final float WIDTH = 16, HEIGHT = 16;
	
    public static Entity createItem(Rectangle rect, ObjectType type, TextureManager manager) {
        return new EntityBuilder<>()
                .withComponent(ComponentPresets.defaultTransform(rect.x, rect.y, WIDTH, HEIGHT))
                .withComponent(ComponentPresets.defaultCollision(rect.width, rect.height))
                .withComponent(ComponentPresets.defaultName(type))
                .withComponent(ComponentPresets.defaultRender(type, manager))
                .withComponent(ComponentPresets.defaultPickup())
                .build();
    }
}
