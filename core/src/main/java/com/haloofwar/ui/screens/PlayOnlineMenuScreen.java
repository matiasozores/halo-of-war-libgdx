package com.haloofwar.ui.screens;

import com.badlogic.gdx.Screen;
import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.enumerators.Background;
import com.haloofwar.launcher.GameInitializer;
import com.haloofwar.ui.Menu;

public class PlayOnlineMenuScreen extends Menu {

	public PlayOnlineMenuScreen(final GameContext CONTEXT, final Screen PREVIOUS_SCREEN) {
		super(CONTEXT, "Jugar en online", new String[] { "Crear partida", "Unirte a partida", "Volver" },
				PREVIOUS_SCREEN, Background.MAIN_MENU);
	}

	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
		case 0:
			this.startNewGame();
			break;

		case 1:
			this.context.getGAME().setScreen(new JoinOnlineMenuScreen(context, this));
			break;

		case 2:
			this.goBack();
			break;

		default:
			System.out.println("Opción inválida: " + optionIndex);
			break;
		}
	}

	private void startNewGame() {
		GameInitializer.initializeGameplay(this.context, null, true);
	}
}
