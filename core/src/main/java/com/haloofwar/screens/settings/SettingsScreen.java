package com.haloofwar.screens.settings;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.screens.MainMenuScreen;
import com.haloofwar.screens.Menu;
import com.haloofwar.screens.settings.audio.AudioScreen;

public class SettingsScreen extends Menu{

	public SettingsScreen(GameContext gameContext) {
		super(gameContext, new String[] {
				"Gráficos",
				"Música y sonidos",
				"Controles",
				"Volver"
			});
	}

	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
			case 0: 
				this.gameContext.getGame().setScreen(new GraphicsScreen(this.gameContext));
				break;
			case 1: 
				this.gameContext.getGame().setScreen(new AudioScreen(this.gameContext));
				break;
			case 2: 
				this.gameContext.getGame().setScreen(new ControlsScreen(this.gameContext));
				break;
			case 3: 
				this.gameContext.getGame().setScreen(new MainMenuScreen(this.gameContext));
				break;
			default:
				break;
		}
	}
}
