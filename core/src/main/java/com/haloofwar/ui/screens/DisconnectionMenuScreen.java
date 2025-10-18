package com.haloofwar.ui.screens;

import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.enumerators.Background;
import com.haloofwar.ui.Menu;

public class DisconnectionMenuScreen extends Menu {
	
    public DisconnectionMenuScreen(final GameContext CONTEXT) {
        super(CONTEXT, "Ha ocurrido un problema con el servidor...",new String[] {
        		"Volver al menu"
        }, null, Background.VICTORY);
    }
    
    
    @Override
    protected void processOption(int optionIndex) {
    	switch (optionIndex) {
		case 0:
			this.context.getGAME().setScreen(new MainMenuScreen(context));

			break;

		default:
			break;
		}
    }
    
    @Override
    public void dispose() {
    	super.dispose();
    }
}
