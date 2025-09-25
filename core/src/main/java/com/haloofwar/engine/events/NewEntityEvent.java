package com.haloofwar.engine.events;

import com.haloofwar.engine.entity.Entity;

public class NewEntityEvent {
	public final Entity entity;

	public NewEntityEvent(Entity entity) {
		this.entity = entity;
	}
}
