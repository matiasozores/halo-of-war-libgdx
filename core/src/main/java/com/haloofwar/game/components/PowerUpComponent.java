package com.haloofwar.game.components;

import com.haloofwar.common.enumerators.PowerUpType;

public class PowerUpComponent implements Component {
	public PowerUpType type;
	public float amount;
	public float duration; 

	public PowerUpComponent(PowerUpType type, float amount, float duration) {
		this.type = type;
		this.amount = amount;
		this.duration = duration;
	}
	
	public PowerUpComponent(PowerUpType type, float amount) {
		this(type, amount, -1);
	}
}
