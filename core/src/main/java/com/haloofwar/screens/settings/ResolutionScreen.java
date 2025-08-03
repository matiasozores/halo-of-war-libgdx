package com.haloofwar.screens.settings;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.screens.Menu;

public class ResolutionScreen extends Menu {

    public ResolutionScreen(GameContext gameContext, Menu previousMenu) {
        super(gameContext, "Resoluciones",new String[]{
                "800x600",
                "1280x720",
                "1600x900",
                "Pantalla completa",
                "Volver"
        }, previousMenu);
    }

    @Override
    protected void processOption(int optionIndex) {
        switch (optionIndex) {
            case 0:
                this.context.getGame().setResolution(800, 600);
                break;
            case 1:
                this.context.getGame().setResolution(1280, 720);
                break;
            case 2:
                this.context.getGame().setResolution(1600, 900);
                break;
            case 3:
                this.context.getGame().setFullscreen();
                break;
            case 4:
                this.goBack(); 
                break;
            default:
                break;
        }
    }
}
