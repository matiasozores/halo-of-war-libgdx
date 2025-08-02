package com.haloofwar.screens;

import com.badlogic.gdx.Screen;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.enumerators.game.GameState;
import com.haloofwar.enumerators.game.SceneType;
import com.haloofwar.game.GameFlowManager;

public class GameManager implements Screen {
	private final GameContext context;
	private final Player player;
	
	private final GameFlowManager flowManager;
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
			
			if (this.flowManager.getGameState() == GameState.PAUSED) {
				this.context.getAudio().getMusic().resume();
				this.flowManager.setGameState(GameState.PLAYING);
			}
		}
	}
	
	public void reset() {
		this.context.getGameplay().dispose();
	}

	@Override
	public void resize(int width, int height) {
		this.flowManager.getCurrentScene().resize(width, height);
		this.pauseMenu.resize(width, height);
	}

	@Override
	public void pause() {
		this.flowManager.getCurrentScene().pause();
	}

	@Override
	public void resume() {
		this.flowManager.getCurrentScene().resume();
	}

	@Override
	public void hide() {
		this.flowManager.getCurrentScene().hide();
	}

	@Override
	public void dispose() {
		this.flowManager.getCurrentScene().dispose();
	}

	// Getters para otras clases
	public GameFlowManager getFlowManager() {
		return this.flowManager;
	}
}
