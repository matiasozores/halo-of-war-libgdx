package com.haloofwar.screens.settings;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.screens.Menu;

public class GraphicsScreen extends Menu {

    public GraphicsScreen(GameContext gameContext, Menu previousMenu) {
        super(gameContext, "Graficos", new String[] {
            "Resolucion",
            "Volver"
        }, previousMenu);
    }
    
    public GraphicsScreen(GameContext gameContext) {
        this(gameContext, null);
    }

    @Override
    protected void processOption(int optionIndex) {
        switch (optionIndex) {
            case 0:
                this.context.getGame().setScreen(new ResolutionScreen(this.context, this));
                break;
            case 1:
                this.goBack();
                break;
            default:
                break;
        }
    }
}
