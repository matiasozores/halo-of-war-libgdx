package com.haloofwar.enumerators.game;

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
