package com.haloofwar.ui.screens;

import com.badlogic.gdx.Gdx;
import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.enums.Background;
import com.haloofwar.game.managers.GameManager;
import com.haloofwar.ui.menus.Menu;

public class GameOverScreen extends Menu{

    private final GameManager manager;

	public GameOverScreen(final GameContext context, final GameManager manager) {
		super(context, "Has muerto...",new String[] {
				"Volver al menu principal",
				"Salir del juego",
			}, null, Background.GAME_OVER);
        this.manager = manager;
	}

	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
			case 0: 
				this.context.getAUDIO().getMusic().stop();
                this.context.getAUDIO().getSound().stopAll();
                this.context.getGAME().setScreen(new MainMenuScreen(this.context));
                this.manager.dispose();
                this.context.resetGameplay();
				break;
			case 1: 
				Gdx.app.exit();
				break;

			default:
				break;
		}
	}

    
}
