package com.haloofwar.screens;

import com.badlogic.gdx.Screen;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.entities.characters.Kratos;
import com.haloofwar.entities.characters.MasterChief;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.enumerators.GameState;
import com.haloofwar.game.GameScene;
import com.haloofwar.game.areas.Tutorial;

public class GameManager implements Screen {
	private Player player;
	private GameScene currentScene;
	private boolean initialized = false;
	private GameState gameState = GameState.PLAYING;

	// Dependencias
	private GameContext gameContext;

	public GameManager(GameContext gameContext) {
		this.gameContext = gameContext;
	}

	@Override
	public void show() {
		if (this.initialized) {
			return;
		}

		int opc = 2;
		switch (opc) {
		case 1:
			this.player = new Kratos(this.gameContext.getInputManager(), this.gameContext.getBulletManager(),
					this.gameContext.getCameraGame(), this.gameContext.getTextureManager(), this.gameContext.getCollisionManager());
			break;
		case 2:
			this.player = new MasterChief(this.gameContext.getInputManager(), this.gameContext.getBulletManager(),
					this.gameContext.getCameraGame(), this.gameContext.getTextureManager(), this.gameContext.getCollisionManager());
			break;
		default:
			this.player = new Kratos(this.gameContext.getInputManager(), this.gameContext.getBulletManager(),
					this.gameContext.getCameraGame(), this.gameContext.getTextureManager(), this.gameContext.getCollisionManager());
			break;
		}

		this.currentScene = new Tutorial(this.gameContext, this.player);
		this.startScene();
		this.initialized = true;
	}

	@Override
	public void render(float delta) {
		switch (this.gameState) {
		case PLAYING:
			this.update(delta);
			this.currentScene.render(delta);
			break;
		case PAUSED:
			this.gameContext.getGame().setScreen(new PauseMenuScreen(this.gameContext, this));
			break;

		case GAME_OVER:
			this.gameContext.getGame().setScreen(new GameOverScreen(this.gameContext));
			break;
		}
	}

	public void update(float delta) {
		if (this.gameContext.getInputManager().isEscape()) {
			if (this.gameState.equals(GameState.PLAYING)) {
				this.gameContext.getMusicManager().pauseMusic();
				this.gameState = GameState.PAUSED;
			} else if (this.gameState.equals(GameState.PAUSED)) {
				this.gameState = GameState.PLAYING;
			}
		}

		if (this.gameContext.getInputManager().isOpenInventory()) {
			this.gameState = GameState.GAME_OVER;
		}

		if (this.gameState == GameState.PLAYING) {
			this.currentScene.update(delta);
		}
	}

	public void startScene() {
		this.currentScene.show();
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	@Override
	public void resize(int width, int height) {
		this.currentScene.resize(width, height);
	}

	@Override
	public void pause() {
		this.currentScene.pause();
	}

	@Override
	public void resume() {
		this.currentScene.resume();
	}

	@Override
	public void hide() {
		this.currentScene.hide();
	}

	@Override
	public void dispose() {
		if (this.currentScene != null) {
			this.currentScene.dispose();
		}
	}

	public GameScene getCurrentScene() {
		return this.currentScene;
	}
}
