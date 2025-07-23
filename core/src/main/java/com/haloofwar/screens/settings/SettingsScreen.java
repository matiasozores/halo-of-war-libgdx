package com.haloofwar.screens.settings;

import com.haloofwar.screens.MainMenuScreen;
import com.haloofwar.screens.Menu;
import com.haloofwar.utilities.GameContext;
import com.haloofwar.utilities.Text;

public class SettingsScreen extends Menu{

	public SettingsScreen(GameContext gameContext) {
		super(gameContext, new Text[] {
			new Text("Gráficos"),
			new Text("Sonido"),
			new Text("Controles"),
			new Text("Volver al menú principal")
		});
	}

	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
			case 0: 
				this.gameContext.getGame().setScreen(new SoundScreen(this.gameContext));
				break;
			case 1: 
				this.gameContext.getGame().setScreen(new SoundScreen(this.gameContext));
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
