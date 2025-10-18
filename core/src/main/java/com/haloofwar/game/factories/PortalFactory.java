package com.haloofwar.game.factories;

import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.common.enumerators.AnimatedObject;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.game.config.ComponentPresets;

public final class PortalFactory {
    public static Entity create(final Rectangle RECTANGLE, final TextureManager TEXTURE, final String TELEPORTATION_TARGET, boolean lastState) {
        final Entity ENTITY = new Entity();
        
        ENTITY.addComponent(ComponentPresets.defaultTransform(-1, RECTANGLE.x, RECTANGLE.y, 64, 64));
        ENTITY.addComponent(ComponentPresets.defaultPortal(TELEPORTATION_TARGET));
        ENTITY.addComponent(ComponentPresets.defaultObjectAnimation(AnimatedObject.PORTAL, TEXTURE, lastState));
        
        return ENTITY;
    }
}
