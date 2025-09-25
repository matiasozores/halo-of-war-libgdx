package com.haloofwar.game.factories;

import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.enums.NPCType;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.game.config.ComponentPresets;

public final class NPCFactory {

    private final TextureManager TEXTURE;
    
    public NPCFactory(GameContext context) {
        this.TEXTURE = context.getTEXTURE();
    }

    public Entity create(final NPCType TYPE, final float X, final float Y) {
    	final Entity ENTITY = new Entity();
    	ENTITY.addComponent(ComponentPresets.defaultTransform(X, Y));
    	ENTITY.addComponent(ComponentPresets.defaultAnimation(TYPE, this.TEXTURE));
    	ENTITY.addComponent(ComponentPresets.defaultHealth());
    	ENTITY.addComponent(ComponentPresets.defaultCollision(X, Y));
    	ENTITY.addComponent(ComponentPresets.defaultName(TYPE));
    	ENTITY.addComponent(ComponentPresets.defaultVillager());
    	ENTITY.addComponent(ComponentPresets.defualtDialogue(TYPE, this.TEXTURE.get(TYPE.getHead())));
    	
    	return ENTITY;
    }
}

