package com.haloofwar.screens;

import com.haloofwar.dependences.GameContext;

public class GameOverScreen extends Menu{

	public GameOverScreen(GameContext gameContext) {
		super(gameContext, new String[] {
				"Reintentar",
				"Salir del juego",
			});
	}

	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
			case 0: 
				this.gameContext.getGame().setScreen(new GameManager(this.gameContext));
				break;
			case 1: 
				this.gameContext.getGame().dispose();
				System.exit(0);
				break;

			default:
				break;
		}
	}


}
