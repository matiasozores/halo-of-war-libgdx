package com.haloofwar.enumerators;

public enum Sprite {
	
	MASTER_CHIEF("masterchief"),
	KRATOS("kratos");
	

	private String folder;
	
	private Sprite(String folder) {
		this.folder = folder;
	}
	
	public String getFolder() {
		return this.folder;
	}
	
}
