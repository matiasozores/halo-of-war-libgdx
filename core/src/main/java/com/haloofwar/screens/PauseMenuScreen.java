package com.haloofwar.screens;

import com.haloofwar.utilities.GameContext;
import com.haloofwar.utilities.Text;

public class PauseMenuScreen extends Menu{

	public PauseMenuScreen(GameContext gameContext) {
		super(gameContext, new Text[] {
				new Text("Reanudar"),
				new Text("Guardar y salir")
		});
	}

	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
			case 0: 
				this.gameContext.getGame().setScreen(new GameManager(this.gameContext));
				break;
			case 1: 
				System.out.println("Guardando y saliendo...");
				break;
			default:
				break;
		}
	}

}
