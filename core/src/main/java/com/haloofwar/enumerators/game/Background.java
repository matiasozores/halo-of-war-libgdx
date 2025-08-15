package com.haloofwar.enumerators.game;

import com.haloofwar.interfaces.entities.EntityDescriptor;

public enum Background implements EntityDescriptor{
	MAIN_MENU("Fondo del menu principal", "images/background/main_menu.png");

	private String name;
	private String path;
	
	private Background(String name, String path) {
		this.name = name;
		this.path = path;
	}
	
	@Override
	public String getPath() {
		return this.path;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	
}
