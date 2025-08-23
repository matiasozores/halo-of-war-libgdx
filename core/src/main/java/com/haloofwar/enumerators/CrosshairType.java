package com.haloofwar.enumerators;

import com.haloofwar.interfaces.EntityDescriptor;

public enum CrosshairType implements EntityDescriptor{
	GREEN("Mira de Master Chief", "ui/crosshairs/crosshair_kratos.png"),
	RED("Mira de Kratos", "ui/crosshairs/crosshair_kratos.png");
	
	private String name;
	private String path;
	
	private CrosshairType(String name, String path) {
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
