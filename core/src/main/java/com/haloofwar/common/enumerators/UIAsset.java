package com.haloofwar.common.enumerators;

import com.haloofwar.engine.entity.EntityDescriptor;

public enum UIAsset implements EntityDescriptor {
	DIALOGUE_BOX("Caja de dialogo", "ui/hud/dialoguebox.png");

	private final String name;
	private final String path;
	
	private UIAsset(final String name, final String path) {
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
