package com.haloofwar.ui.screens;

import com.badlogic.gdx.Gdx;
import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.enums.Background;
import com.haloofwar.common.enums.MusicTrack;
import com.haloofwar.engine.events.PlayMusicEvent;
import com.haloofwar.ui.menus.Menu;

public class MainMenuScreen extends Menu{

	public MainMenuScreen(final GameContext CONTEXT) {
		super(CONTEXT, "Menu Principal",new String[] {
				"Jugar",
				"Configuracion",
				"Salir"
			}, null, Background.MAIN_MENU);
		
        this.context.getGLOBAL_BUS().publish(new PlayMusicEvent(MusicTrack.MAIN));
	}

	@Override
	protected void processOption(int optionIndex) {
	    switch (optionIndex) {
	        case 0: 
	            this.context.getGAME().setScreen(new PlayMenuScreen(this.context, this));
	            break;
	        case 1: 
	            this.context.getGAME().setScreen(new SettingsScreen(this.context, this));
	            break;
	        case 2: 
	            Gdx.app.exit();
	            break;
	        default:
	            break;
	    }
	}
}
