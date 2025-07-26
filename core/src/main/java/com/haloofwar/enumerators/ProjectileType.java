package com.haloofwar.enumerators;

import com.haloofwar.interfaces.TextureDescriptor;

public enum ProjectileType implements TextureDescriptor{
	
	BULLET("sprites/projectiles/bullet.png");
	
	private String path;

	private ProjectileType(String path) {
		this.path = path;
	}
	
	@Override
	public String getPath() {
		return this.path;
	}
}
