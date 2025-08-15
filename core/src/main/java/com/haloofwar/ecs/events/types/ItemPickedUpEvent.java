package com.haloofwar.ecs.events.types;

import com.haloofwar.ecs.Entity;

public class ItemPickedUpEvent {
	public final Entity item;

	public ItemPickedUpEvent(Entity item) {
		this.item = item;
	}
}
