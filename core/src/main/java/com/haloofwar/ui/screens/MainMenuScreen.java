package com.haloofwar.ui.screens;

import com.badlogic.gdx.Gdx;
import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.enumerators.Background;
import com.haloofwar.common.enumerators.MusicTrack;
import com.haloofwar.engine.events.PlayMusicEvent;
import com.haloofwar.ui.Menu;

public class MainMenuScreen extends Menu{

	public MainMenuScreen(final GameContext CONTEXT) {
		super(CONTEXT, "Menu Principal",new String[] {
				"Jugar online",
				"Jugar en solitario",
				"Configuracion",
				"Salir"
			}, null, Background.MAIN_MENU);
		
        this.context.getGlobalBus().publish(new PlayMusicEvent(MusicTrack.MAIN));
	}

	@Override
	protected void processOption(int optionIndex) {
	    switch (optionIndex) {
	    	case 0: 
            this.context.getGAME().setScreen(new PlayOnlineMenuScreen(this.context, this));
            break;
	    
	        case 1: 
	        	System.out.println("Clausurado");
	            break;
	        case 2: 
	            this.context.getGAME().setScreen(new SettingsScreen(this.context, this));
	            break;
	        case 3: 
	            Gdx.app.exit();
	            break;
	        default:
	            break;
	    }
	}
}
