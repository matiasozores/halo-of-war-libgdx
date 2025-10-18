package com.haloofwar.engine.events;

import com.haloofwar.common.enumerators.InventoryItemStatus;
import com.haloofwar.engine.entity.Entity;

public class UpdateInventoryEvent {

	public final int identifier;
	public final Entity item;
	public final int quantity;
	public final InventoryItemStatus status;
	
	public UpdateInventoryEvent(final int identifier, Entity item, int quantity, InventoryItemStatus status) {
		this.identifier = identifier;
		this.item = item;
		this.quantity = quantity;
		this.status = status;
	}
	
}
