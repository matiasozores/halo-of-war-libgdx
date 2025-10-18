package com.haloofwar.common.enumerators;

import com.haloofwar.engine.entity.EntityDescriptor;

public enum HeadType implements EntityDescriptor{
	KRATOS("Cabeza de Kratos","ui/hud/kratosHUD.png"),
	MASTER_CHIEF("Cabeza de Master Chief","ui/hud/jefeHUD.png");
	
	private final String path;
	private final String name;
	
	private HeadType(final String name, final String path) {
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
