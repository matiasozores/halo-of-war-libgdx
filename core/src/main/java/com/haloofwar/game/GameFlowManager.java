package com.haloofwar.game;

import java.util.HashSet;
import java.util.Set;

import com.haloofwar.components.TransformComponent;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.dependences.SceneManager;
import com.haloofwar.enumerators.GameState;
import com.haloofwar.enumerators.LevelType;
import com.haloofwar.enumerators.SceneType;
import com.haloofwar.events.ChangeSceneEvent;
import com.haloofwar.events.EnterLevelEvent;
import com.haloofwar.events.EventBus;
import com.haloofwar.events.GameStateEvent;
import com.haloofwar.events.LevelCompletedEvent;
import com.haloofwar.events.PlayerDiedEvent;
import com.haloofwar.interfaces.Scene;

public class GameFlowManager {
	private Scene currentScene;
	public GameState currentState;
	private SceneManager sceneManager;
	
	private TransformComponent playerTransform;
	private float lastPlayerXAtLobby = 0f;
	private float lastPlayerYAtLobby = 0f;
	
	private Set<LevelType> completedLevels = new HashSet<>();
	
	public GameFlowManager(GameContext context) {
		EventBus bus = context.getGameplay().getBus();
		
		bus.subscribe(PlayerDiedEvent.class, this::onPlayerDied);
		bus.subscribe(GameStateEvent.class, this::onChangeState);
		bus.subscribe(EnterLevelEvent.class, this::onEnterLevel);
		bus.subscribe(LevelCompletedEvent.class, this::onLevelCompleted);
		bus.subscribe(ChangeSceneEvent.class, this::onChangeScene);
		bus.publish(new GameStateEvent(GameState.PLAYING));
		
		this.sceneManager = context.getScene();
		this.playerTransform = context.getGameplay().getPlayer().getComponent(TransformComponent.class);
	}
	
	public GameFlowManager(GameContext context, Set<LevelType> completedLevels) {
		this(context);
		this.completedLevels = completedLevels;
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
        if (event.getLevelType() != null) {
            this.completedLevels.add(event.getLevelType());
        }

        if (this.playerTransform != null) {
            this.playerTransform.x = this.lastPlayerXAtLobby;
            this.playerTransform.y = this.lastPlayerYAtLobby;
        }

        this.changeScene(this.sceneManager.get(SceneType.MAIN));
    }

    public void onEnterLevel(EnterLevelEvent event) {
        this.lastPlayerXAtLobby = this.playerTransform.x;
        this.lastPlayerYAtLobby = this.playerTransform.y;	

        LevelType levelType = event.type;
        
        if(this.completedLevels.contains(levelType)) {
        	return;
        }
        
        GameScene targetScene = this.sceneManager.get(levelType);
        Level level = targetScene instanceof Level ? (Level) targetScene : null;

        if(level == null) {
            return;
        }
        
        this.changeScene(targetScene);
    }
    
    public void onChangeScene(ChangeSceneEvent event) {
		this.changeScene(event.getNextScene());
	}

    public void changeScene(Scene newScene) {
        if(this.currentScene != null) {
            this.currentScene.hide();
        }

        this.currentScene = newScene;

        if(this.currentScene != null) {
            this.currentScene.show();
            this.currentScene.reconfigureCamera();
        }
        if(newScene instanceof GameScene) {
        	GameScene scene = (GameScene) newScene;
        	this.playerReposition(scene.getWorld());
        }
        
        this.setGameState(GameState.PLAYING);
    }
    
	private void playerReposition(World world) {
		float x = world.getMap().getMetaData().getxSpawnPoint();
		float y = world.getMap().getMetaData().getySpawnPoint();
		
		this.playerTransform.setPosition(x, y);
	}

	public void setGameState(GameState state) {
		this.currentState = state;
	}
	
	public Scene getCurrentScene() {
		return this.currentScene;
	}
	
	public Set<LevelType> getCompletedLevels() {
	    return this.completedLevels;
	}
}
