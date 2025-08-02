package com.haloofwar.enumerators.animation;

import com.haloofwar.interfaces.TextureDescriptor;

public enum HeadType implements TextureDescriptor{
	KRATOS("ui/hud/kratosHUD.png"),
	MASTER_CHIEF("ui/hud/jefeHUD.png");
	
	private String path;
	
	private HeadType(String path) {
		this.path = path;
	}

	@Override
	public String getPath() {
		return this.path;
	}
}
