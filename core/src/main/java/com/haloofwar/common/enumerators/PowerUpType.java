package com.haloofwar.common.enumerators;

import com.haloofwar.engine.entity.EntityDescriptor;

public enum PowerUpType implements EntityDescriptor {
	SOBRE_ESCUDO("Sobre-escudo", "images/objects/potion.png", 50f, -1f),
	INVISIBILIDAD("Invisibilidad", "images/objects/potion.png", 0f, 10f);
	
	private final String name;
	private final String path;
	private final float amount;
	private final float duration;
	
	private PowerUpType(final String name, final String path, final float amount, final float duration) {
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
	
    public static PowerUpType getByName(final String NAME) {
    	boolean found = false;
    	PowerUpType type = null;
    	int i = 0;
    	final PowerUpType powerUps[] = PowerUpType.values();
    	
    	while(i < powerUps.length && !found) {
    		if(powerUps[i].getName().equals(NAME)) {
    			found = true;
    			type = powerUps[i];
    		} else {
    			i++;
    		}
    	}
    	
    	return type;
    }
}
