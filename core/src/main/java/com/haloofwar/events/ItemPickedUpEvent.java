package com.haloofwar.events;

import com.haloofwar.components.Entity;

public class ItemPickedUpEvent {
	public final Entity item;

	public ItemPickedUpEvent(Entity item) {
		this.item = item;
	}
}
