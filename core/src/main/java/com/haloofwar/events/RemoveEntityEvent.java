package com.haloofwar.events;

import com.haloofwar.components.Entity;

public class RemoveEntityEvent {
	public final Entity entity;
	
	public RemoveEntityEvent(Entity entity) {
		this.entity = entity;
	}
}
