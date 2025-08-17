package com.haloofwar.screens;

import com.badlogic.gdx.Screen;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.ecs.Entity;
import com.haloofwar.enumerators.game.GameState;
import com.haloofwar.enumerators.game.SceneType;
import com.haloofwar.enumerators.game.SoundType;
import com.haloofwar.game.GameFlowManager;

public class GameManager implements Screen {
	private final GameContext context;
	private final Entity player;
	
	private final GameFlowManager flowManager;
	private final PauseMenuScreen pauseMenu;
	
	// ----------------------------------------------------

	public GameManager(GameContext context, Entity player) {
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
		this.flowManager.update(delta);
		
		GameState state = this.flowManager.getGameState();

		switch (state) {
			case PAUSED:
				this.context.getGame().setScreen(this.pauseMenu);
				return;
			case GAME_OVER:
				this.context.getAudio().getMusic().stop();
				this.context.getAudio().getSound().stopAll();
				this.context.getAudio().getSound().play(SoundType.GAME_OVER);
				this.context.getGame().setScreen(new GameOverScreen(this.context));
				return;
			case PLAYING:
			case WAITING:
				this.flowManager.render(delta);
				break;
		}
	}

	public void reset() {
		this.context.disposeScene();
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
