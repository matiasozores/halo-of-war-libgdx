package com.haloofwar.enumerators.game;

import com.haloofwar.interfaces.TextureDescriptor;

public enum Background implements TextureDescriptor{
	MAIN_MENU("images/background/main_menu.png");

	private String path;
	
	private Background(String path) {
		this.path = path;
	}
	
	@Override
	public String getPath() {
		return this.path;
	}
	
	
}
