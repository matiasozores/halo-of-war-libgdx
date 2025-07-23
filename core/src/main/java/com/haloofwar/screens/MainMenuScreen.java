package com.haloofwar.screens;

import com.haloofwar.screens.settings.SettingsScreen;
import com.haloofwar.utilities.GameContext;
import com.haloofwar.utilities.Text;

public class MainMenuScreen extends Menu{

	public MainMenuScreen(GameContext gameContext) {
		super(gameContext, new Text[] {
			new Text("Jugar"),
			new Text("Configuracion"),
			new Text("Salir")
		});
	}

	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
			case 0: 
				this.gameContext.getGame().setScreen(new GameManager(this.gameContext));
				break;
			case 1: 
				this.gameContext.getGame().setScreen(new SettingsScreen(this.gameContext));
				break;
			case 2: 
				this.gameContext.getGame().dispose();
				System.exit(0);
				break;
			default:
				break;
		}
	}


}
