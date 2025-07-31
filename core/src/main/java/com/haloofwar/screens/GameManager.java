package com.haloofwar.screens;

import com.badlogic.gdx.Screen;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.enumerators.GameState;
import com.haloofwar.enumerators.SceneType;
import com.haloofwar.factories.GameFlowManager;

public class GameManager implements Screen {
	private final GameContext context;
	private final GameFlowManager flowManager;
	private final Player player;
	private final PauseMenuScreen pauseMenu;

	public GameManager(GameContext context, Player player) {
		this.context = context;
		this.player = player;
		
		this.pauseMenu = new PauseMenuScreen(context, this);
		
		this.flowManager = new GameFlowManager(context);
		this.flowManager.startGame(this.player, SceneType.TUTORIAL);
	}

	@Override
	public void show() {
		this.flowManager.getCurrentScene().show();
	}

	@Override
	public void render(float delta) {
		this.handleInput();

		if (this.flowManager.getGameState() == GameState.PAUSED) {
			this.context.getGame().setScreen(this.pauseMenu);
			return;
		}

		if (this.flowManager.getGameState() == GameState.GAME_OVER) {
			this.context.getGame().setScreen(new GameOverScreen(this.context));
			return;
		}

		this.flowManager.update(delta);
		this.flowManager.render(delta);
	}

	private void handleInput() {
		if (this.context.getInput().isEscape()) {
			if (this.flowManager.getGameState() == GameState.PLAYING) {
				this.context.getAudio().getMusic().pause();
				this.flowManager.setGameState(GameState.PAUSED);
			}
		}

		if (this.context.getInput().isOpenInventory()) {
			this.flowManager.setGameState(GameState.GAME_OVER);
		}
	}
	
	public void reset() {
		this.context.getGameplay().dispose();
		this.context.getCollision().clear();
	}

	@Override
	public void resize(int width, int height) {
		flowManager.getCurrentScene().resize(width, height);
		pauseMenu.resize(width, height);
	}

	@Override
	public void pause() {
		flowManager.getCurrentScene().pause();
	}

	@Override
	public void resume() {
		flowManager.getCurrentScene().resume();
	}

	@Override
	public void hide() {
		flowManager.getCurrentScene().hide();
	}

	@Override
	public void dispose() {
		flowManager.getCurrentScene().dispose();
	}

	// Getters para otras clases
	public GameFlowManager getFlowManager() {
		return flowManager;
	}
}
