package com.haloofwar.engine.events.online;

import com.haloofwar.common.enumerators.PowerUpType;

public class SpawnPowerUpEventOnline {
	public final int identifier;
	public final PowerUpType powerUp;
	public final float x;
	public final float y;
	
	public SpawnPowerUpEventOnline(final int identifier, PowerUpType powerUp, float x, float y) {
		this.identifier = identifier;
		this.powerUp = powerUp;
		this.x = x;
		this.y = y;
	}
}
