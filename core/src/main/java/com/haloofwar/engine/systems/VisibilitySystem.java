package com.haloofwar.engine.systems;

import com.haloofwar.engine.entity.Entity;
import com.haloofwar.game.components.VisibilityComponent;
import com.haloofwar.interfaces.Updatable;

public class VisibilitySystem extends BaseSystem implements Updatable {
	
	@Override
	public void register(Entity entity) {
		if(entity.hasComponent(VisibilityComponent.class)) {
			super.register(entity);
		}
	}

	@Override
	public void update(float delta) {
		for(Entity entity : this.ENTITIES) {
			VisibilityComponent visibilityComp = entity.getComponent(VisibilityComponent.class);

			if(visibilityComp.getInvisibleDuration() > 0) {
				visibilityComp.setInvisible(visibilityComp.getInvisibleDuration() - delta);
			} else {
				visibilityComp.setInvisible(0f);
			}
		}
	}
	
	
}
