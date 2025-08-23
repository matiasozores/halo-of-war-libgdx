package com.haloofwar.enumerators;

import com.haloofwar.interfaces.EntityDescriptor;

public enum Key implements EntityDescriptor {
	E("Tecla E", "ui/gameplay/e_key.png");

	private String name;
	private String path;
	
	private Key(String name, String path) {
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
