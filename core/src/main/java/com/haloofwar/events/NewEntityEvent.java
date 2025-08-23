package com.haloofwar.events;

import com.haloofwar.components.Entity;

public class NewEntityEvent {
	public final Entity entity;

	public NewEntityEvent(Entity entity) {
		this.entity = entity;
	}
}
