package com.haloofwar.screens;

import com.badlogic.gdx.Gdx;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.enumerators.SoundType;
import com.haloofwar.screens.settings.SettingsScreen;

public class MainMenuScreen extends Menu{

	public MainMenuScreen(GameContext gameContext) {
		super(gameContext, "Menu Principal",new String[] {
				"Jugar",
				"Configuracion",
				"Salir"
			}, null);
	}

	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
			case 0: 
				this.startGame();
				break;
			case 1: 
				this.context.getGame().setScreen(new SettingsScreen(this.context, this));
				break;
			case 2: 
				this.context.getGame().dispose();
				Gdx.app.exit();
				break;
			default:
				break;
		}
		

	}
	
	private void startGame() {
		this.context.getAudio().getSound().play(SoundType.LOAD_GAME);
		this.context.getAudio().getMusic().stop();
		this.context.getGame().setScreen(new PlayerSelectionScreen(this.context, this));
	}
}
