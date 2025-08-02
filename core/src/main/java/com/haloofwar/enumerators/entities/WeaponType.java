package com.haloofwar.enumerators.entities;

import com.haloofwar.interfaces.TextureDescriptor;

public enum WeaponType implements TextureDescriptor{
	RIFLE_ASALTO("ui/hud/armaHUD.png"),
	SWORD("ui/hud/espadaHUD.png");
	
	private String path;

	private WeaponType(String path) {
		this.path = path;
	}
	
	@Override
	public String getPath() {
		return this.path;
	}
	
	
}
