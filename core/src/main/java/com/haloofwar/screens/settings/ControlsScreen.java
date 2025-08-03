package com.haloofwar.screens.settings;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.screens.Menu;

public class ControlsScreen extends Menu {

    public ControlsScreen(GameContext gameContext, Menu previousMenu) {
        super(gameContext, "Controles", new String[] {
            "Moverse: Flechas",
            "Atacar: Click izquierdo",
            "Interactuar: E",
            "Inventario: I",
            "Volver"
        }, previousMenu);
    }

    @Override
    protected void processOption(int optionIndex) {
        if (optionIndex == 4) {
            this.goBack();
        }
    }
}
