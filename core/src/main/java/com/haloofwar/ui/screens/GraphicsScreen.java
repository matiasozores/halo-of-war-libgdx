package com.haloofwar.ui.screens;

import com.badlogic.gdx.Screen;
import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.enums.Background;
import com.haloofwar.ui.menus.Menu;

public class GraphicsScreen extends Menu {

    public GraphicsScreen(final GameContext CONTEXT, final Screen PREVIOUS_SCREEN) {
        super(CONTEXT, "Graficos", new String[] {
            "Resolucion",
            "Volver"
        }, PREVIOUS_SCREEN, Background.PORTAL_MENU);
    }
    
    public GraphicsScreen(final GameContext CONTEXT) {
        this(CONTEXT, null);
    }

    @Override
    protected void processOption(int optionIndex) {
        switch (optionIndex) {
            case 0:
                this.context.getGAME().setScreen(new ResolutionScreen(this.context, this));
                break;
            case 1:
                this.goBack();
                break;
            default:
                break;
        }
    }
}
