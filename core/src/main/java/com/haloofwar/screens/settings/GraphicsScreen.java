package com.haloofwar.screens.settings;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.screens.Menu;

public class GraphicsScreen extends Menu {

	public GraphicsScreen(GameContext gameContext) {
		super(gameContext, new String[] {
				"Resolución",
				"Volver",
			});
	}

	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
			case 0:
				this.gameContext.getGame().setScreen(new ResolutionScreen(this.gameContext));
				break;
				
			case 1: 
				this.gameContext.getGame().setScreen(new SettingsScreen(this.gameContext));
				break;
			default:
				break;
		}
	}

}
