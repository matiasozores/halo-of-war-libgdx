package com.haloofwar.ui.screens;

import com.badlogic.gdx.Screen;
import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.enums.Background;
import com.haloofwar.common.enums.PlayerType;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.game.data.SaveGameData;
import com.haloofwar.launcher.GameInitializer;
import com.haloofwar.ui.menus.Menu;

public class PlayMenuScreen extends Menu {

    public PlayMenuScreen(final GameContext CONTEXT, final Screen PREVIOUS_SCREEN) {
        super(CONTEXT, "Jugar", new String[] {
                "Nueva Partida",
                "Cargar Partida",
                "Modo infinito (extremo)",
                "Volver"
        }, PREVIOUS_SCREEN, Background.MAIN_MENU);
    }

    @Override
    protected void processOption(int optionIndex) {
        switch (optionIndex) {
            case 0:
                this.startNewGame();
                break;

            case 1:
                this.loadSavedGame();
                break;
                
            case 2:
            	this.startInfinityGame();
            	break;

            case 3:
                this.goBack();
                break;

            default:
                System.out.println("Opción inválida: " + optionIndex);
                break;
        }
    }

    private void startNewGame() {
        this.context.getGAME().setScreen(new PlayerSelectionScreen(this.context, this));
    }

    private void loadSavedGame() {
        final SaveGameData DATA = this.context.getSAVE().loadFromFile();

        if (DATA == null) {
            System.out.println("No se ha podido cargar un archivo...");
            return;
        }

        final Entity KRATOS = GameInitializer.createPlayerFromSave(this.context, DATA, PlayerType.KRATOS);
        final Entity MASTER_CHIEF = GameInitializer.createPlayerFromSave(this.context, DATA, PlayerType.MASTER_CHIEF);
        GameInitializer.initializeGameplay(this.context, KRATOS, MASTER_CHIEF, DATA.levelsCompleted);
    }

	private void startInfinityGame() {
//	    Entity player1 = GameInitializer.createPlayer(CONTEXT, PlayerType.MASTER_CHIEF);
//	    Entity player2 = GameInitializer.createPlayer(CONTEXT, PlayerType.KRATOS);
//	    GameInitializer.initializeGameplay(CONTEXT, player1, player2, null);
//	    CONTEXT.getGameplay().getBus().publish(new EnterLevelEvent(LevelSceneType.INFINITY));
	}
}
