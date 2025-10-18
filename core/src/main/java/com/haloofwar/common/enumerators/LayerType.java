package com.haloofwar.common.enumerators;

public enum LayerType {
	OBSTACLE("collision"),
	ITEM("items"),
	PORTAL("portal");
	
	private final String name;
	
	private LayerType(final String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
