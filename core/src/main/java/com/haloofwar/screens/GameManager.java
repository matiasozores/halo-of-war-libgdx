package com.haloofwar.screens;

import com.badlogic.gdx.Screen;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.enumerators.GameState;
import com.haloofwar.enumerators.SceneType;
import com.haloofwar.enumerators.SoundType;
import com.haloofwar.game.GameFlowManager;

public class GameManager implements Screen {
	private final GameContext context;
	private final GameFlowManager flowManager;
	private final PauseMenuScreen pauseMenu;
	
	// ----------------------------------------------------

	public GameManager(GameContext context) {
		this.context = context;

		this.flowManager = new GameFlowManager(context);
		this.flowManager.startGame(this.context.getFactories().getSCENE_FACTORY().create(SceneType.TUTORIAL));
	
		this.pauseMenu = new PauseMenuScreen(context, this);
	}

	@Override
	public void show() {
		this.flowManager.getCurrentScene().show();
	}

	@Override
	public void render(float delta) {	
		this.flowManager.update(delta);
		
		GameState state = this.flowManager.currentState;

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
