package com.haloofwar.engine.events.online;

import com.haloofwar.common.enumerators.InventoryItemStatus;
import com.haloofwar.common.enumerators.ObjectType;
import com.haloofwar.common.enumerators.PlayerType;

public class InventoryUpdateEventOnline {
	public final int identifier;
	public final ObjectType itemType;
	public final int quantity;
	public final PlayerType playerType;
	public final InventoryItemStatus status;
	
	public InventoryUpdateEventOnline(final int identifier, ObjectType itemType, int quantity, PlayerType playerType, InventoryItemStatus status) {
		this.identifier = identifier;
		this.itemType = itemType;
		this.quantity = quantity;
		this.playerType = playerType;
		this.status = status;
	}
	
	public InventoryUpdateEventOnline(ObjectType itemType, int quantity, PlayerType playerType, InventoryItemStatus status) {
		this(-1, itemType, quantity, playerType, status);
	}
}
