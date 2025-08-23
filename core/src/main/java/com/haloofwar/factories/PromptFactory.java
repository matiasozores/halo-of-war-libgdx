package com.haloofwar.factories;

import com.haloofwar.components.Entity;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.enumerators.Key;
import com.haloofwar.factories.components.ComponentPresets;
import com.haloofwar.factories.components.EntityBuilder;

public final class PromptFactory {
	private final GameContext context;

    public PromptFactory(GameContext context) {
        this.context = context;
    }

	public Entity create(Key key, float x, float y, float offset) {
        return new EntityBuilder<>(this.context)
                .withComponent(ComponentPresets.defaultTransform(x, y))
                .withComponent(ComponentPresets.defaultRender(key, this.context.getTexture()))
                .withComponent(ComponentPresets.defaultPrompt(offset))
                .build();
	}


}
