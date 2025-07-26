package com.haloofwar.screens.settings;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.screens.Menu;

public class ResolutionScreen extends Menu{
	
	public ResolutionScreen(GameContext gameContext) {
		super(gameContext, new String[] {
				"800x600",
				"1280x720",
				"1600x900",
				"Pantalla completa",
				"Volver"
			});
	}
	
	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
			case 0: 
				System.out.println("Resolución 800x600 seleccionada.");
				this.gameContext.getGame().setResolution(800, 600);
				break;
			case 1:
				System.out.println("Resolución 1280x720 seleccionada.");
				this.gameContext.getGame().setResolution(1280, 720);
				break;
			case 2: 
				System.out.println("Resolución 1600x900 seleccionada.");
				this.gameContext.getGame().setResolution(1600, 900);
				break;

			case 3:
				System.out.println("Resolución pantalla completa seleccionada.");
				this.gameContext.getGame().setFullscreen();
				break;
				
			case 4:
				this.gameContext.getGame().setScreen(new GraphicsScreen(this.gameContext));
				break;
			default:
				break;
		}
	}
}
