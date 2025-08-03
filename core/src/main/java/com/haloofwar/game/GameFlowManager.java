package com.haloofwar.game;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.entities.players.Player;
import com.haloofwar.enumerators.game.GameState;
import com.haloofwar.enumerators.game.SceneType;
import com.haloofwar.factories.SceneFactory;

public class GameFlowManager {
	private GameScene currentScene;
	private GameState currentState = GameState.WAITING;
	private final GameContext context;
	
	public GameFlowManager(GameContext context) {
		this.context = context;
	}
	
	public void startGame(Player player, SceneType initialScene) {
		this.currentScene = SceneFactory.create(initialScene, this.context, player);
		this.currentScene.show();
		this.currentState = GameState.PLAYING;
	}
	
	public void update(float delta) {
		this.handleInput();

		switch (this.currentState) {
			case PLAYING:
				this.currentScene.update(delta);
				break;
			case PAUSED:
			case WAITING:
			case GAME_OVER:
				break;
		}
	}

	private void handleInput() {
		if (this.context.getInput().isEscape()) {
			if (this.currentState == GameState.PLAYING) {
				this.context.getAudio().getMusic().pause();
				this.currentState = GameState.PAUSED;
			} else if (this.currentState == GameState.PAUSED) {
				this.context.getAudio().getMusic().resume();
				this.currentState = GameState.PLAYING;
			}
		}
	}


	
	public void render(float delta) {
		if(this.currentScene != null) {
			this.currentScene.render(delta);
		}
	}
	
	public void setGameState(GameState state) {
		this.currentState = state;
	}
	
	public GameState getGameState() {
		return this.currentState;
	}
	
	public GameScene getCurrentScene() {
		return this.currentScene;
	}
}
