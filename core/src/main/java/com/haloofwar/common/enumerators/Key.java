package com.haloofwar.common.enumerators;

import com.haloofwar.engine.entity.EntityDescriptor;

public enum Key implements EntityDescriptor {
	E("Tecla E", "ui/gameplay/e_key.png");

	private final String name;
	private final String path;
	
	private Key(final String name, final String path) {
		this.name = name;
		this.path = path;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getPath() {
		return this.path;
	}
	
	
}
