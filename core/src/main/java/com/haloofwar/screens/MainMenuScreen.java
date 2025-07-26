package com.haloofwar.screens;

import com.haloofwar.audio.MusicManager;
import com.haloofwar.audio.SoundManager;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.enumerators.SoundType;
import com.haloofwar.screens.settings.SettingsScreen;

public class MainMenuScreen extends Menu{

	public MainMenuScreen(GameContext gameContext) {
		super(gameContext, new String[] {
				"Jugar",
				"Configuración",
				"Salir"
			});
	}

	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
			case 0: 
				this.startGame();
				break;
			case 1: 
				this.gameContext.getGame().setScreen(new SettingsScreen(this.gameContext));
				break;
			case 2: 
				this.gameContext.getGame().dispose();
				System.exit(0);
				break;
			default:
				break;
		}
	}
	
	private void startGame() {
		final SoundManager SOUND = this.gameContext.getSoundManager();
		final MusicManager MUSIC = this.gameContext.getMusicManager();
		
		SOUND.play(SoundType.LOAD_GAME);
		MUSIC.stopMusic();
		this.gameContext.getGame().setScreen(new GameManager(this.gameContext));
	}
}
