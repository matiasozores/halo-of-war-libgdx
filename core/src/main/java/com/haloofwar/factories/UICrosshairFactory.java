package com.haloofwar.factories;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.enumerators.PlayerType;
import com.haloofwar.ui.Crosshair;

public class UICrosshairFactory {
	private final GameContext context;

	public UICrosshairFactory(GameContext context) {
		this.context = context;
	}
	
	public Crosshair create(PlayerType type) {
		return new Crosshair(type.getCrosshair(), context.getGameplay().getCamera(), this.context.getTexture());
	}
	
}
