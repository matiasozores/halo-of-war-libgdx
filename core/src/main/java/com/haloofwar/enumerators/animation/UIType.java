package com.haloofwar.enumerators.animation;

import com.haloofwar.interfaces.TextureDescriptor;

public enum UIType implements TextureDescriptor{
	INTERACT("ui/gameplay/interact.png");

	private String path;
	
	private UIType(String path) {
		this.path = path;
	}
	
	@Override
	public String getPath() {
		return this.path;
	}
}
