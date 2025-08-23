package com.haloofwar.components;

import com.haloofwar.interfaces.Component;

public class StockComponent implements Component{
	private int stock = 1;
	
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
}
