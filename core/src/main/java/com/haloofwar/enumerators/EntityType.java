package com.haloofwar.enumerators;

public enum EntityType {
	
	MASTER_CHIEF("masterchief", 6, 8),
	KRATOS("kratos", 4, 4);
	
	private int idleLength, walkLength;
	private String folder;
	
	private EntityType(String folder, int idleLength, int walkLength) {
		this.folder = folder;
		this.idleLength = idleLength;
		this.walkLength = walkLength;
	}
	
	
	
	public String getFolder() {
		return this.folder;
	}
	
	public int getIdleLength() {
		return this.idleLength;
	}
	
	public int getWalkLength() {
		return this.walkLength;
	}
	
}
