package com.haloofwar.game.factories;

import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.enums.Key;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.game.config.ComponentPresets;

public final class PromptFactory {
	private final TextureManager TEXTURE;

    public PromptFactory(final GameContext CONTEXT) {
        this.TEXTURE = CONTEXT.getTEXTURE();
    }

	public Entity create(final Key KEY, final float X, final float Y, final float OFFSET) {
        final Entity ENTITY = new Entity();
        ENTITY.addComponent(ComponentPresets.defaultTransform(X, Y));
        ENTITY.addComponent(ComponentPresets.defaultRender(KEY, this.TEXTURE));
        ENTITY.addComponent(ComponentPresets.defaultPrompt(OFFSET));
        return ENTITY;
	}


}
