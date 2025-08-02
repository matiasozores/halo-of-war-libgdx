package com.haloofwar.enumerators.entities.behavior;

import com.haloofwar.interfaces.TextureDescriptor;

public enum CrosshairType implements TextureDescriptor{
	GREEN("ui/crosshairs/crosshair_kratos.png"),
	RED("ui/crosshairs/crosshair_kratos.png");
	
	private String path;
	
	private CrosshairType(String path) {
		this.path = path;
	}
	
	@Override
	public String getPath() {
		return this.path;
	}
}
