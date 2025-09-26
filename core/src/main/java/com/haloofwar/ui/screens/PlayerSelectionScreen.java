package com.haloofwar.ui.screens;

import com.badlogic.gdx.Screen;
import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.enums.Background;
import com.haloofwar.common.enums.PlayerType;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.launcher.GameInitializer;
import com.haloofwar.ui.menus.Menu;

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

        PlayerType selectedPlayer = this.PLAYER_OPTIONS[optionIndex];
        PlayerType otherPlayer = this.chooseOtherPlayer(selectedPlayer);

        GameInitializer.initializeGameplay(this.context, this.createPlayer(selectedPlayer), this.createPlayer(otherPlayer), null);
    }
    
    private PlayerType chooseOtherPlayer(final PlayerType SELECTED_PLAYER) {
    	switch (SELECTED_PLAYER) {
		case KRATOS:
			return PlayerType.MASTER_CHIEF;
			
		case MASTER_CHIEF:
			return PlayerType.KRATOS;
			
		default:
			return PlayerType.KRATOS;
		}
    }

    private Entity createPlayer(PlayerType type) {
    	return this.context.getFACTORIES().getPLAYER_FACTORY().create(type);
    }
}
