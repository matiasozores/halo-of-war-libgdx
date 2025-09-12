package com.haloofwar.enumerators;

import com.haloofwar.interfaces.EntityDescriptor;

public enum CutSceneType implements EntityDescriptor {
	PLAYA_PERDIDA("Playa Perdida", "images/background/main_menu.png"),
	CASTILLO("Playa Perdida", "ui/hud/jefeHUD.png");

	private final String name;
	private final String path;
	
	private CutSceneType(String name, String path) {
		this.name = name;
		this.path = path;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getPath() {
		return this.path;
	}
	
}
