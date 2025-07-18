package com.haloofwar.enumerators;

public enum EntityType {
	
	MASTER_CHIEF("masterchief"),
	KRATOS("kratos");
	

	private String folder;
	
	private EntityType(String folder) {
		this.folder = folder;
	}
	
	public String getFolder() {
		return this.folder;
	}
	
}
