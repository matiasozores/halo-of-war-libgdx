package com.haloofwar.factories;

import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.components.Entity;
import com.haloofwar.dependences.TextureManager;
import com.haloofwar.enumerators.HeadType;
import com.haloofwar.factories.components.ComponentPresets;
import com.haloofwar.factories.components.EntityBuilder;

public final class PortalFactory {
    public static Entity create(Rectangle rect, TextureManager manager, String teleportationTarget) {
        return new EntityBuilder<>()
                .withComponent(ComponentPresets.defaultTransform(rect.x, rect.y, rect.width, rect.height))
                .withComponent(ComponentPresets.defaultCollision(rect.width, rect.height))
                .withComponent(ComponentPresets.defaultPortal(teleportationTarget))
                .withComponent(ComponentPresets.defaultRender(HeadType.KRATOS, manager))
                .build();
    }
}
