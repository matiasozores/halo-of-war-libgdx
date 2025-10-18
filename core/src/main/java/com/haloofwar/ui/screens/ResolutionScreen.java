package com.haloofwar.ui.screens;

import com.badlogic.gdx.Screen;
import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.enumerators.Background;
import com.haloofwar.ui.Menu;

public class ResolutionScreen extends Menu {

    public ResolutionScreen(final GameContext CONTEXT, final Screen PREVIOUS_SCREEN) {
        super(CONTEXT, "Resoluciones",new String[]{
                "800x600",
                "1280x720",
                "1600x900",
                "Pantalla completa",
                "Volver"
        }, PREVIOUS_SCREEN, Background.PORTAL_MENU);
    }

    @Override
    protected void processOption(int optionIndex) {
        switch (optionIndex) {
            case 0:
                this.context.getGAME().setResolution(800, 600);
                break;
            case 1:
                this.context.getGAME().setResolution(1280, 720);
                break;
            case 2:
                this.context.getGAME().setResolution(1600, 900);
                break;
            case 3:
                this.context.getGAME().setFullscreen();
                break;
            case 4:
                this.goBack(); 
                break;
            default:
                break;
        }
    }
}
