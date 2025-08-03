package com.haloofwar.screens;

import com.badlogic.gdx.Gdx;
import com.haloofwar.dependences.GameContext;

public class GameOverScreen extends Menu{

	public GameOverScreen(GameContext gameContext) {
		super(gameContext, "Has muerto...",new String[] {
				"Volver al menu principal",
				"Salir del juego",
			}, null);
	}

	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
			case 0: 
				this.context.disposeScene();
				this.context.getGame().setScreen(new MainMenuScreen(this.context));
				break;
			case 1: 
				Gdx.app.exit();
				break;

			default:
				break;
		}
	}


}
