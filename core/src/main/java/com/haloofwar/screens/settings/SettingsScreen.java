package com.haloofwar.screens.settings;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.screens.Menu;

public class SettingsScreen extends Menu {

    public SettingsScreen(GameContext gameContext, Menu previousMenu) {
        super(gameContext, "Configuracion",new String[] {
            "Graficos",
            "Musica y sonidos",
            "Controles",
            "Volver"
        }, previousMenu);
    }

    public SettingsScreen(GameContext gameContext) {
        this(gameContext, null);
    }
    
    @Override
    protected void processOption(int optionIndex) {
        switch (optionIndex) {
            case 0:
                this.context.getGame().setScreen(new GraphicsScreen(this.context, this));
                break;
            case 1:
                this.context.getGame().setScreen(new AudioScreen(this.context, this));
                break;
            case 2:
                this.context.getGame().setScreen(new ControlsScreen(this.context, this));
                break;
            case 3:
                this.goBack();
                break;
            default:
                break;
        }
    }
}
