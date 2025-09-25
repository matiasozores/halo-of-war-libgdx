package com.haloofwar.common.enums;

public enum LayerType {
	OBSTACLE("collision"),
	ITEM("items"),
	PORTAL("portal");
	
	private String name;
	
	private LayerType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
