package com.haloofwar.ui.menus;

import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.enumerators.Background;
import com.haloofwar.ui.Menu;

public class ServerScreen extends Menu{
	public ServerScreen(final GameContext CONTEXT) {
		super(CONTEXT, "Corriendo Servidor", Background.PORTAL_MENU);
	}

    @Override
    public void dispose(){
        super.dispose();
    }
}
