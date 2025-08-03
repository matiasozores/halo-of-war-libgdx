package com.haloofwar.enumerators.entities.objects;

import com.haloofwar.interfaces.TextureDescriptor;

public enum ItemType implements TextureDescriptor {
	POSION_SIN_EFECTOS("images/objects/potion.png");

	private String path;
	
	private ItemType(String path) {
		this.path = path;
	}
	
	@Override
	public String getPath() {
		return this.path;
	}
	
	
}
