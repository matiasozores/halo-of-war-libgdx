package com.haloofwar.ecs.events.types.items;

import com.haloofwar.ecs.Entity;

public class ItemPickedUpEvent {
	public final Entity item;

	public ItemPickedUpEvent(Entity item) {
		this.item = item;
	}
}
