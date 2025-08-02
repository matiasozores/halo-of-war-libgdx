package com.haloofwar.game;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.entities.characters.Player;
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
		if (this.context.getInput().isEscape()) {
			if (this.currentState == GameState.PLAYING) {
				this.context.getAudio().getMusic().pause();
				this.currentState = GameState.PAUSED;
				return;
			} else if (this.currentState == GameState.PAUSED) {
				this.context.getAudio().getMusic().resume();
				this.currentState = GameState.PLAYING;
				return;
			}
		}

		switch (this.currentState) {
			case PLAYING:
				this.currentScene.update(delta);
				break;
			case PAUSED:
				break;
			case WAITING:
				break;
			case GAME_OVER:
				break;
			default:
				break;
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
