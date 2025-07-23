package com.haloofwar.screens.settings;

import com.haloofwar.screens.Menu;
import com.haloofwar.utilities.GameContext;
import com.haloofwar.utilities.Text;

public class GraphicsScreen extends Menu {

	public GraphicsScreen(GameContext gameContext) {
		super(gameContext, new Text[] {
			new Text("Resolución"),
			new Text("Volver al menú principal")
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
