package com.haloofwar.ui.screens;

import com.badlogic.gdx.Screen;
import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.enumerators.Background;
import com.haloofwar.common.enumerators.PlayerType;
import com.haloofwar.ui.Menu;

public class PlayerSelectionScreen extends Menu {

    private final PlayerType[] PLAYER_OPTIONS = {
        PlayerType.KRATOS,      
        PlayerType.MASTER_CHIEF  
    };

    private final int BACK_OPTION = 2;

    public PlayerSelectionScreen(final GameContext CONTEXT, final Screen PREVIOUS_SCREEN) {
        super(CONTEXT, "Seleccion de jugador", new String[] {
            "Kratos",
            "Master Chief",
            "Volver"
        }, PREVIOUS_SCREEN, Background.PLAYER_SELECTION);
    }

    @Override
    protected void processOption(int optionIndex) {
        if (optionIndex == this.BACK_OPTION) {
            this.goBack();
            return;
        }

        if (optionIndex < 0 || optionIndex >= this.PLAYER_OPTIONS.length) {
            System.out.println("Opción inválida: " + optionIndex);
            return;
        }


       // GameInitializer.initializeGameplay(this.context, this.createPlayer(selectedPlayer), this.createPlayer(otherPlayer), null, false);
    }
    
}
