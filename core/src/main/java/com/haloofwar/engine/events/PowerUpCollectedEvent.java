package com.haloofwar.engine.events;

import com.haloofwar.engine.entity.Entity;

public class PowerUpCollectedEvent {
	public final Entity player;
	public final Entity powerUp;
	
	public PowerUpCollectedEvent(Entity player, Entity powerUp) {
		this.player = player;
		this.powerUp = powerUp;
	}
}
