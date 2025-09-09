package com.haloofwar.game;

import com.haloofwar.components.PortalComponent;
import com.haloofwar.components.TransformComponent;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.dependences.SceneManager;
import com.haloofwar.enumerators.GameState;
import com.haloofwar.enumerators.LevelType;
import com.haloofwar.enumerators.SceneType;
import com.haloofwar.events.EnterLevelEvent;
import com.haloofwar.events.GameStateEvent;
import com.haloofwar.events.LevelCompletedEvent;
import com.haloofwar.events.PlayerDiedEvent;

public class GameFlowManager {
	private GameScene currentScene;
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
        // Guardar la posición actual del jugador antes de volver al lobby
    	if(this.playerTransform != null) {
    		this.playerTransform.x = this.lastPlayerXAtLobby;
    		this.playerTransform.y = this.lastPlayerYAtLobby;
    	}

        if (this.currentScene != null) {
            this.currentScene.hide();
        }

        // Volver al lobby
        this.currentScene = this.sceneManager.get(SceneType.MAIN);
        this.currentScene.show();
        this.currentScene.reconfigureCamera();


        this.setGameState(GameState.PLAYING);
    }
    
    public void onEnterLevel(EnterLevelEvent event) {
    	System.out.println(this.playerTransform);
    	this.lastPlayerXAtLobby = this.playerTransform.x;
    	this.lastPlayerYAtLobby = this.playerTransform.y;	
   
        PortalComponent portal = event.portal.getComponent(PortalComponent.class);
        LevelType levelType = portal.targetScene;
        
        // Verificar si el nivel ya fue completado ------------
        
        GameScene targetScene = this.sceneManager.get(levelType);
        Level level = null;
        if(targetScene instanceof Level) {
			level = (Level) targetScene;
		}
        
        if(level != null) {
        	if(level.isLevelCompleted()) {
        		System.out.println("El nivel " + levelType + " ya fue completado. No se puede entrar de nuevo.");
            	return;
            }
		}
        // ----------------------------------------------------
        
        
        // Liberar escena anterior
        if (this.currentScene != null) {
            this.currentScene.hide();
        }

        // Crear la nueva escena desde SceneManager
        this.currentScene = targetScene;

        // Inicializar y setear el estado
        this.currentScene.show();
        this.currentScene.reconfigureCamera();
        this.setGameState(GameState.PLAYING);
    }


	public void setGameState(GameState state) {
		this.currentState = state;
	}
	
	public GameScene getCurrentScene() {
		return this.currentScene;
	}
	
	
}
