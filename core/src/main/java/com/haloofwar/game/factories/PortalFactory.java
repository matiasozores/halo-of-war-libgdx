package com.haloofwar.game.factories;

import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.common.enums.HeadType;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.game.config.ComponentPresets;

public final class PortalFactory {
    public static Entity create(final Rectangle RECTANGLE, final TextureManager TEXTURE, final String TELEPORTATION_TARGET) {
        final Entity ENTITY = new Entity();
        
        ENTITY.addComponent(ComponentPresets.defaultTransform(RECTANGLE.x, RECTANGLE.y, RECTANGLE.width, RECTANGLE.height));
        ENTITY.addComponent(ComponentPresets.defaultCollision(RECTANGLE.width, RECTANGLE.height));
        ENTITY.addComponent(ComponentPresets.defaultPortal(TELEPORTATION_TARGET));
        ENTITY.addComponent(ComponentPresets.defaultRender(HeadType.KRATOS, TEXTURE));
        
        return ENTITY;
    }
}
