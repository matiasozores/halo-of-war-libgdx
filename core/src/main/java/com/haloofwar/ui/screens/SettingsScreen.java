package com.haloofwar.ui.screens;

import com.badlogic.gdx.Screen;
import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.enumerators.Background;
import com.haloofwar.ui.Menu;

public class SettingsScreen extends Menu {

    public SettingsScreen(final GameContext CONTEXT, final Screen PREVIOUS_SCREEN) {
        super(CONTEXT, "Configuracion",new String[] {
            "Graficos",
            "Musica y sonidos",
            "Controles",
            "Volver"
        }, PREVIOUS_SCREEN, Background.PORTAL_MENU);
    }

    public SettingsScreen(GameContext gameContext) {
        this(gameContext, null);
    }
    
    @Override
    protected void processOption(int optionIndex) {
        switch (optionIndex) {
            case 0:
                this.context.getGAME().setScreen(new GraphicsScreen(this.context, this));
                break;
            case 1:
                this.context.getGAME().setScreen(new AudioScreen(this.context, this));
                break;
            case 2:
                this.context.getGAME().setScreen(new ControlsScreen(this.context, this));
                break;
            case 3:
                this.goBack();
                break;
            default:
                break;
        }
    }
}
