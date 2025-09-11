package com.haloofwar.game;

import com.haloofwar.components.PortalComponent;
import com.haloofwar.components.TransformComponent;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.dependences.SceneManager;
import com.haloofwar.enumerators.GameState;
import com.haloofwar.enumerators.LevelType;
import com.haloofwar.enumerators.SceneType;
import com.haloofwar.events.ChangeSceneEvent;
import com.haloofwar.events.EnterLevelEvent;
import com.haloofwar.events.GameStateEvent;
import com.haloofwar.events.LevelCompletedEvent;
import com.haloofwar.events.PlayerDiedEvent;
import com.haloofwar.interfaces.Scene;

public class GameFlowManager {
	private Scene currentScene;
	public GameState currentState = GameState.WAITING;
	private SceneManager sceneManager;
	
	private TransformComponent playerTransform;
	private float lastPlayerXAtLobby = 0f;
	private float lastPlayerYAtLobby = 0f;
	
	public GameFlowManager(GameContext context) {
		context.getBus().subscribe(PlayerDiedEvent.class, this::onPlayerDied);
		context.getBus().subscribe(GameStateEvent.class, this::onChangeState);
		context.getBus().subscribe(EnterLevelEvent.class, this::onEnterLevel);
		context.getBus().subscribe(LevelCompletedEvent.class, this::onLevelCompleted);
		context.getBus().subscribe(ChangeSceneEvent.class, this::onChangeScene);
		
		this.sceneManager = context.getScene();
		this.playerTransform = context.getGameplay().getPlayer().getComponent(TransformComponent.class);
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
				break;
			case WAITING:
				this.currentScene.update(delta);
				break;
			case GAME_OVER:
				break;
			case CUTSCENE:
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
    
    public void onChangeState(GameStateEvent event) {
        this.currentState = event.getState();
    }
    
    private void onLevelCompleted(LevelCompletedEvent event) {
        if(this.playerTransform != null) {
            this.playerTransform.x = this.lastPlayerXAtLobby;
            this.playerTransform.y = this.lastPlayerYAtLobby;
        }

        this.changeScene(this.sceneManager.get(SceneType.MAIN));
    }

    public void onEnterLevel(EnterLevelEvent event) {
        this.lastPlayerXAtLobby = this.playerTransform.x;
        this.lastPlayerYAtLobby = this.playerTransform.y;	

        PortalComponent portal = event.portal.getComponent(PortalComponent.class);
        LevelType levelType = portal.targetScene;
        
        GameScene targetScene = this.sceneManager.get(levelType);
        Level level = targetScene instanceof Level ? (Level) targetScene : null;
        
        if(level != null && level.isLevelCompleted()) {
            return;
        }

        this.changeScene(targetScene);
    }
    
    public void onChangeScene(ChangeSceneEvent event) {
		this.changeScene(event.getNextScene());
	}

    private void changeScene(Scene newScene) {
        if(this.currentScene != null) {
            this.currentScene.hide();
        }

        this.currentScene = newScene;

        if(this.currentScene != null) {
            this.currentScene.show();
            this.currentScene.reconfigureCamera();
        }

        this.setGameState(GameState.PLAYING);
    }

	public void setGameState(GameState state) {
		this.currentState = state;
	}
	
	public Scene getCurrentScene() {
		return this.currentScene;
	}
}
