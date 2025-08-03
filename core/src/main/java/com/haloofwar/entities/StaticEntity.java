package com.haloofwar.entities;

import com.haloofwar.entities.components.EntityStateHandler;

public abstract class StaticEntity extends Entity{
	public StaticEntity(String name, int width, int height, EntityStateHandler state) {
		super(name, width, height, state);
	}
	
	public StaticEntity(String name, float x, float y, int width, int height, EntityStateHandler state) {
		super(name, x, y, width, height, state);

	}
	
	public void kill() {
		this.active = false;
		this.state.onDeath(this);
	}
	
}
