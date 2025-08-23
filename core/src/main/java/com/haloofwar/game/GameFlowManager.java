package com.haloofwar.game;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.enumerators.GameState;
import com.haloofwar.events.GameStateEvent;
import com.haloofwar.events.PlayerDiedEvent;

public class GameFlowManager {
	private GameScene currentScene;
	public GameState currentState = GameState.WAITING;
	
	public GameFlowManager(GameContext context) {
		context.getBus().subscribe(PlayerDiedEvent.class, this::onPlayerDied);
		context.getBus().subscribe(GameStateEvent.class, this::onChangeState);
	}
	
	public void startGame(GameScene initialScene) {
		this.currentScene = initialScene;
		this.currentScene.show();
		this.currentState = GameState.PLAYING;
	}
	
	public void update(float delta) {
		switch (this.currentState) {
			case PLAYING:
				this.currentScene.update(delta);
				break;
			case PAUSED:
			case WAITING:
				this.currentScene.update(delta);
			case GAME_OVER:
				break;
		}
	}


	public void render(float delta) {
		if(this.currentScene != null) {
			this.currentScene.render(delta);
		}
	}

    private void onPlayerDied(PlayerDiedEvent event) {
        this.setGameState(GameState.GAME_OVER);
    }
    
    private void onChangeState(GameStateEvent event) {
        this.currentState = event.getState();
    }


	public void setGameState(GameState state) {
		this.currentState = state;
	}
	
	public GameScene getCurrentScene() {
		return this.currentScene;
	}
}
