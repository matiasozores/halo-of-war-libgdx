package com.haloofwar.ecs.components.collision;

import com.haloofwar.ecs.components.Component;

public class PickupComponent implements Component{
	private int stock = 1;
	private boolean pickedUp = false;
	
	public void affectStock(final int MOUNT_AFFECT) {
		if(this.stock + MOUNT_AFFECT < 0) {
			System.out.println("Error, no se puede restar el monto porque da negativo...");
		} else {
			this.stock += MOUNT_AFFECT;
		}
	}
	
	public int getStock() {
		return this.stock;
	}
	
	public boolean isPickedUp() {
		return this.pickedUp;
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}
}
