package com.haloofwar.screens.settings;

import com.haloofwar.screens.Menu;
import com.haloofwar.utilities.GameContext;
import com.haloofwar.utilities.Text;

public class ResolutionScreen extends Menu{
	
	public ResolutionScreen(GameContext gameContext) {
		super(gameContext, new Text[] {
			new Text("1280x720"),
			new Text("1600x900"),
			new Text("1920x1080"),
			new Text("Volver al menú principal")
		});
	}
	
	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
			case 0: 
				System.out.println("Resolución 1280x720 seleccionada.");
				this.gameContext.getGame().setResolution(1280, 720);
				break;
			case 1:
				System.out.println("Resolución 1600x900 seleccionada.");
				this.gameContext.getGame().setResolution(1600, 900);
				break;
			case 2: 
				System.out.println("Resolución 1920x1080 seleccionada.");
				this.gameContext.getGame().setResolution(1920, 1080);
				break;
			case 3:
				this.gameContext.getGame().setScreen(new SettingsScreen(this.gameContext));
				break;
			default:
				break;
		}
	}
}
