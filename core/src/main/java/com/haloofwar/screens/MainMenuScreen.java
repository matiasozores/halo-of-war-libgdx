package com.haloofwar.screens;

import com.badlogic.gdx.Gdx;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.enumerators.game.MusicTrack;
import com.haloofwar.screens.settings.SettingsScreen;

public class MainMenuScreen extends Menu{

	public MainMenuScreen(GameContext gameContext) {
		super(gameContext, "Menu Principal",new String[] {
				"Jugar",
				"Configuracion",
				"Salir"
			}, null);
		
        gameContext.getAudio().getMusic().play(MusicTrack.MAIN);
	}

	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
			case 0: 
				this.context.getGame().setScreen(new PlayerSelectionScreen(this.context, this));
				break;
			case 1: 
				this.context.getGame().setScreen(new SettingsScreen(this.context, this));
				break;
			case 2: 
				Gdx.app.exit();
				break;
			default:
				break;
		}
		

	}
}
