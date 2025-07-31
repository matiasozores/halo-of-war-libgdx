package com.haloofwar.screens;

import com.badlogic.gdx.Gdx;
import com.haloofwar.dependences.GameContext;

public class GameOverScreen extends Menu{

	public GameOverScreen(GameContext gameContext) {
		super(gameContext, "Has fracasado...",new String[] {
				"Volver al menu principal",
				"Salir del juego",
			}, null);
	}

	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
			case 0: 
				this.context.dispose();
				this.context.getGame().setScreen(new MainMenuScreen(this.context));
				break;
			case 1: 
				this.context.dispose();
				this.context.getGame().dispose();
				Gdx.app.exit();
				break;

			default:
				break;
		}
	}


}
