package com.haloofwar.enumerators.entities;

import com.haloofwar.interfaces.TextureDescriptor;

public enum ObjectType implements TextureDescriptor{
	POTION("images/objects/potion.png");

	private String path;
	
	private ObjectType(String path) {
		this.path = path;
	}
	
	@Override
	public String getPath() {
		return this.path;
	}


	
}
