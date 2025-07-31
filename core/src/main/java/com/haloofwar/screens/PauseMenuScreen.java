package com.haloofwar.screens;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.enumerators.GameState;
import com.haloofwar.screens.settings.SettingsScreen;

public class PauseMenuScreen extends Menu {

	private final GameManager gameManager;

	public PauseMenuScreen(GameContext gameContext, GameManager gameManager) {
		super(gameContext, "Menu Pausa", new String[] {
			"Reanudar",
			"Configuracion",
			"Guardar y volver al menu principal"
		}, null);
		this.gameManager = gameManager;
	}

	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
			case 0:
				this.gameManager.getFlowManager().setGameState(GameState.PLAYING);
				this.context.getAudio().getMusic().resume();
				this.context.getGame().setScreen(this.gameManager); 
				break;
				
			case 1:
				this.context.getGame().setScreen(new SettingsScreen(this.context, this));
				break;

			case 2: 
				this.gameManager.reset();
				this.context.getGame().setScreen(new MainMenuScreen(this.context));
				break;

			default:
				break;
		}
	}
}
