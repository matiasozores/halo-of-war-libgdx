package com.haloofwar.factories;

import com.haloofwar.components.Entity;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.enumerators.NPCType;
import com.haloofwar.factories.components.ComponentPresets;
import com.haloofwar.factories.components.EntityBuilder;
import com.haloofwar.interfaces.EntityDescriptor;
import com.haloofwar.interfaces.EntityFactory;

public final class NPCFactory implements EntityFactory {

    private final GameContext context;

    public NPCFactory(GameContext context) {
        this.context = context;
    }

    @Override
    public Entity create(EntityDescriptor type, float x, float y) {
        if (!(type instanceof NPCType npcType)) {
            throw new IllegalArgumentException("Tipo inválido para PlayerFactory");
        }
        
        return new EntityBuilder<>(this.context)
                .withComponent(ComponentPresets.defaultTransform(x, y))
                .withComponent(ComponentPresets.defaultAnimation(npcType, this.context.getTexture()))
                .withComponent(ComponentPresets.defaultHealth())
                .withComponent(ComponentPresets.defaultCollision(x, y))
                .withComponent(ComponentPresets.defaultName(npcType))
                .withComponent(ComponentPresets.defaultVillager())
                .withComponent(ComponentPresets.defualtDialogue(npcType, this.context.getTexture().get(npcType.getHead())))
                .build();
    }
}

