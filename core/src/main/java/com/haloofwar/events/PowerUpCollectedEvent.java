package com.haloofwar.events;

import com.haloofwar.components.Entity;

public class PowerUpCollectedEvent {
	public final Entity player;
	public final Entity powerUp;
	
	public PowerUpCollectedEvent(Entity player, Entity powerUp) {
		this.player = player;
		this.powerUp = powerUp;
	}
}
