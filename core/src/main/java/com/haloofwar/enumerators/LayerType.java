package com.haloofwar.enumerators;

public enum LayerType {
	OBSTACLE("collision"),
	ITEM("items");
	
	private String name;
	
	private LayerType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
