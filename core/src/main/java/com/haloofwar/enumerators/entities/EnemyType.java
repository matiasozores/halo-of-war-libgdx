package com.haloofwar.enumerators.entities;

import com.haloofwar.interfaces.EntityDescriptor;

public enum EnemyType implements EntityDescriptor{
	ELITE("sprites/elite.png", 2, 8);

	private String path;
	private int idleLength;
	private int walkLength;
	
	private EnemyType(String path, int idleLength, int walkLength) {
		this.path = path;
		this.idleLength = idleLength;
		this.walkLength = walkLength;
	}
	
	@Override
	public String getPath() {
		return this.path;
	}

	@Override
	public int getIdleLength() {
		return this.idleLength;
	}

	@Override
	public int getWalkLength() {
		return this.walkLength;
	}
}
