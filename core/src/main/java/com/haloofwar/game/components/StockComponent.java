package com.haloofwar.game.components;

import com.haloofwar.common.enumerators.ObjectType;

public class StockComponent implements Component{
	private int stock = 1;
	private ObjectType type;
	
	public StockComponent(ObjectType type) {
		this.type = type;
	}

	public void affectStock(final int MOUNT_AFFECT) {
		if(this.stock + MOUNT_AFFECT < 0) {
			System.out.println("Error, no se puede restar el stock porque da negativo...");
		} else {
			this.stock += MOUNT_AFFECT;
		}
	}
	
	public int getStock() {
		return this.stock;
	}
	
	public void setStock(final int STOCK) {
		this.stock = STOCK;
	}
	
	public ObjectType getType() {
		return this.type;
	}
}
