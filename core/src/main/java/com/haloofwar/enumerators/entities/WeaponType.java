package com.haloofwar.enumerators.entities;

import com.haloofwar.interfaces.entities.EntityDescriptor;

public enum WeaponType implements EntityDescriptor{
	RIFLE_ASALTO("Rifle de asalto", "ui/hud/armaHUD.png"),
	ESPADA("Espada", "ui/hud/espadaHUD.png");
	
	private String name;
	private String path;

	private WeaponType(String name, String path) {
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
