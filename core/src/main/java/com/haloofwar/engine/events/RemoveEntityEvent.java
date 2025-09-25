package com.haloofwar.engine.events;

import com.haloofwar.engine.entity.Entity;

public class RemoveEntityEvent {
	public final Entity entity;
	
	public RemoveEntityEvent(Entity entity) {
		this.entity = entity;
	}
}
