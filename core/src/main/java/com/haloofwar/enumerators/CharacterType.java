package com.haloofwar.enumerators;

import com.haloofwar.interfaces.EntityDescriptor;

public enum CharacterType implements EntityDescriptor {
	
	MASTER_CHIEF("masterchief", 6, 8, "ui/crosshairs/crosshair_masterchief.png"),
	KRATOS("kratos", 4, 4, "ui/crosshairs/crosshair_kratos.png");
	
	private int idleLength, walkLength;
	private String path, crosshairPath;

	private CharacterType(String folder, int idleLength, int walkLength, String crosshairPath) {
		this.path = folder;
		this.idleLength = idleLength;
		this.walkLength = walkLength;
		this.crosshairPath = crosshairPath;
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
	
	public String getCrosshairPath() {
		return this.crosshairPath;
	}
}
