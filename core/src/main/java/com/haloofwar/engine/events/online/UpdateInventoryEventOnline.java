package com.haloofwar.engine.events.online;

import com.haloofwar.common.enumerators.InventoryItemStatus;
import com.haloofwar.common.enumerators.ObjectType;
import com.haloofwar.common.enumerators.PlayerType;

public class UpdateInventoryEventOnline {
	public final int identifier;
	public final ObjectType itemType;
	public final int quantity;
	public final PlayerType playerType;
	public final InventoryItemStatus status;
	
	public UpdateInventoryEventOnline(final int identifier, PlayerType type, ObjectType itemType, int quantity, InventoryItemStatus status) {
		this.identifier = identifier;
		this.itemType = itemType;
		this.quantity = quantity;
		this.playerType = type;
		this.status = status;
	}
}
