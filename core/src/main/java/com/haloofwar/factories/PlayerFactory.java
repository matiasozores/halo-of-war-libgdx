package com.haloofwar.factories;

import com.haloofwar.components.Entity;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.enumerators.PlayerType;
import com.haloofwar.factories.components.ComponentPresets;
import com.haloofwar.factories.components.EntityBuilder;
import com.haloofwar.interfaces.EntityDescriptor;
import com.haloofwar.interfaces.EntityFactory;

public final class PlayerFactory implements EntityFactory {

    private final GameContext context;

    public PlayerFactory(GameContext context) {
        this.context = context;
    }

    @Override
    public Entity create(EntityDescriptor type, float x, float y) {
        if (!(type instanceof PlayerType playerType)) {
            throw new IllegalArgumentException("Tipo inválido para PlayerFactory");
        }
        
        return new EntityBuilder<>(this.context)
                .withComponent(ComponentPresets.defaultTransform(x, y))
                .withComponent(ComponentPresets.defaultAnimation(playerType, this.context.getTexture()))
                .withComponent(ComponentPresets.defaultHealth())
                .withComponent(ComponentPresets.defaultCollision(x, y))
                .withComponent(ComponentPresets.playerMovement(this.context.getBus()))
                .withComponent(ComponentPresets.defaultCrosshair(playerType, this.context.getTexture(), this.context.getWorldCamera()))
                .withComponent(ComponentPresets.defaultName(playerType))
                .withComponent(ComponentPresets.defaultInventory())
                .withComponent(ComponentPresets.defaultWeapon(playerType))
                .withComponent(ComponentPresets.defaultPlayer())
                .build();
    }
}
