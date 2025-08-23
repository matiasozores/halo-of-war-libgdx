package com.haloofwar.enumerators;

import com.haloofwar.interfaces.EntityDescriptor;

public enum ObjectType implements EntityDescriptor {
	POSION_SIN_EFECTOS("images/objects/potion.png", "Posion sin efectos");

	private final String path;
	private final String name;

	ObjectType(String path, String name) {
		this.path = path;
		this.name = name;
	}

	@Override
	public String getPath() {
		return this.path;
	}

	public String getName() {
		return name;
	}
}
