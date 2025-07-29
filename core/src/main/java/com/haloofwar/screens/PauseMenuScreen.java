package com.haloofwar.screens;

import com.badlogic.gdx.Gdx;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.enumerators.GameState;

public class PauseMenuScreen extends Menu{

	private GameManager gameManager;
	
	public PauseMenuScreen(GameContext gameContext, GameManager gameManager) {
		super(gameContext, new String[] {
				"Reanudar",
				"Guardar y salir",
			});
		
		this.gameManager = gameManager;
	}

	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
			case 0: 
				this.gameManager.setGameState(GameState.PLAYING);
	        	this.gameContext.getMusicManager().resumeMusic();
				this.gameContext.getGame().setScreen(this.gameManager);
				break;
			case 1: 
				this.gameContext.getGame().dispose();
				Gdx.app.exit();
				break;
			default:
				break;
		}
	}

}
