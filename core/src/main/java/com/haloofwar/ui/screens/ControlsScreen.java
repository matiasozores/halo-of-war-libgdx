package com.haloofwar.ui.screens;

import com.badlogic.gdx.Screen;
import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.enumerators.Background;
import com.haloofwar.ui.Menu;

public class ControlsScreen extends Menu {

    public ControlsScreen(final GameContext CONTEXT, final Screen PREVIOUS_SCREEN) {
        super(CONTEXT, "Controles", new String[] {
            "Moverse: Flechas",
            "Atacar: Click izquierdo",
            "Interactuar: E",
            "Inventario: I",
            "Volver"
        }, PREVIOUS_SCREEN, Background.PORTAL_MENU);
    }

    @Override
    protected void processOption(int optionIndex) {
        if (optionIndex == 4) {
            this.goBack();
        }
    }
}
