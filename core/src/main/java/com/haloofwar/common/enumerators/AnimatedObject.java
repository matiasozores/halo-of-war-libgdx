package com.haloofwar.common.enumerators;

import com.haloofwar.engine.entity.AnimatedEntityDescriptor;

public enum AnimatedObject implements AnimatedEntityDescriptor {
	PORTAL("Portal","sprites/portal.png", 5, 5);

	private String name;
	private String path;
	private int idleLength;
	private int walkLength;
	
	private AnimatedObject(String name, String path, int idleLength, int walkLength) {
		this.name = name;
		this.path = path;
		this.idleLength = idleLength;
		this.walkLength = walkLength;
	}
	
	@Override
	public String getName() {
		return this.name;
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
