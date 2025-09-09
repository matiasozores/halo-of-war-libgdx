package com.haloofwar.enumerators;

import com.haloofwar.interfaces.EntityDescriptor;

public enum PowerUpType implements EntityDescriptor {
	SOBRE_ESCUDO("Sobre-escudo", "images/objects/potion.png", 50f, -1f),
	INVISIBILIDAD("Invisibilidad", "images/objects/potion.png", 0f, 10f);
	
	private String name;
	private String path;
	private float amount;
	private float duration;
	
	private PowerUpType(String name, String path, float amount, float duration) {
		this.name = name;
		this.path = path;
		this.amount = amount;
		this.duration = duration;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	@Override
	public String getPath() {
		return this.path;
	}

	public float getAmount() {
		return this.amount;
	}
	
	public float getDuration() {
		return this.duration;
	}
}
