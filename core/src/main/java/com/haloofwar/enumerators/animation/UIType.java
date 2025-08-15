package com.haloofwar.enumerators.animation;

import com.haloofwar.interfaces.entities.EntityDescriptor;

public enum UIType implements EntityDescriptor{
	INTERACT("Letrero de interaccion", "ui/gameplay/interact.png");

	private final String path;
	private final String name;
	
	private UIType(String name, String path) {
		this.name = name;
		this.path = path;
	}
	
	@Override
	public String getPath() {
		return this.path;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
}
