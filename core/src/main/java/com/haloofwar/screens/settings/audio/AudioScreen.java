package com.haloofwar.screens.settings.audio;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.screens.Menu;
import com.haloofwar.screens.settings.SettingsScreen;

public class AudioScreen extends Menu {

    public AudioScreen(GameContext gameContext) {
		super(gameContext, new String[] {
				"Música",
				"Sonidos",
				"Volver"
			});
    }

    @Override
    protected void processOption(int optionIndex) {
		switch (optionIndex) {
		case 0: 
			this.gameContext.getGame().setScreen(new MusicScreen(this.gameContext));
			break;
		case 1: 
			this.gameContext.getGame().setScreen(new SoundScreen(this.gameContext));
			break;
		case 2: 
			this.gameContext.getGame().setScreen(new SettingsScreen(this.gameContext));
			break;
		default:
			break;
		}
    }





}
