package com.haloofwar.enumerators;

import com.haloofwar.interfaces.EntityDescriptor;

public enum UIAsset implements EntityDescriptor {
	DIALOGUE_BOX("Caja de dialogo", "ui/hud/dilaoguebox.png");

	private final String name;
	private final String path;
	
	private UIAsset(String name, String path) {
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
