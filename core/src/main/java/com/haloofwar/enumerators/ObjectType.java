package com.haloofwar.enumerators;

import com.haloofwar.interfaces.TextureDescriptor;

public enum ObjectType implements TextureDescriptor{
	CHEST("LOGO.png");

	private String path;
	
	private ObjectType(String path) {
		this.path = path;
	}
	
	@Override
	public String getPath() {
		return this.path;
	}


	
}
