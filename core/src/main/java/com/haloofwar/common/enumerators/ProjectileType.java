package com.haloofwar.common.enumerators;

import com.haloofwar.engine.entity.EntityDescriptor;

public enum ProjectileType implements EntityDescriptor{
	
	BULLET("Bala basica","sprites/projectiles/bullet.png");
	
	private final String name;
	private final String path;
	
	private ProjectileType(final String name, final String path) {
		this.path = path;
		this.name = name;
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
