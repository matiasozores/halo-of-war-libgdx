package com.haloofwar.common.enumerators;

import com.haloofwar.engine.entity.EntityDescriptor;

public enum CrosshairType implements EntityDescriptor{
	GREEN("Mira de Master Chief", "ui/crosshairs/crosshair_kratos.png"),
	RED("Mira de Kratos", "ui/crosshairs/crosshair_kratos.png");
	
	private final String name;
	private final String path;
	
	private CrosshairType(final String name, final String path) {
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
