package com.haloofwar.enumerators.entities;

import com.haloofwar.interfaces.entities.AnimatedEntityDescriptor;

public enum EnemyType implements AnimatedEntityDescriptor {
	ELITE("Elite", "sprites/elite.png", 2, 8);

	private final String name;
	private final String path;
	private final int idleLength;
	private final int walkLength;

	private EnemyType(String name, String path, int idleLength, int walkLength) {
		this.name = name;
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
	
	@Override
	public String getName() {
		return this.name;
	}
}
