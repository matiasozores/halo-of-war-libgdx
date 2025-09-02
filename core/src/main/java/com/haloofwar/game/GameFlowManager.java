package com.haloofwar.game;

import com.haloofwar.components.PortalComponent;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.dependences.SceneManager;
import com.haloofwar.enumerators.GameState;
import com.haloofwar.enumerators.SceneType;
import com.haloofwar.events.EnterLevelEvent;
import com.haloofwar.events.GameStateEvent;
import com.haloofwar.events.PlayerDiedEvent;

public class GameFlowManager {
	private GameScene currentScene;
	public GameState currentState = GameState.WAITING;
	private SceneManager sceneManager;
	
	public GameFlowManager(GameContext context) {
		context.getBus().subscribe(PlayerDiedEvent.class, this::onPlayerDied);
		context.getBus().subscribe(GameStateEvent.class, this::onChangeState);
		context.getBus().subscribe(EnterLevelEvent.class, this::onEnterLevel);
		this.sceneManager = context.getScene();
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
    
    public void onEnterLevel(EnterLevelEvent event) {
        PortalComponent portal = event.portal.getComponent(PortalComponent.class);
        SceneType sceneType = portal.targetScene;

        // Liberar escena anterior
        if (this.currentScene != null) {
            this.currentScene.hide();
            this.currentScene.dispose();
        }

        // Crear la nueva escena desde SceneManager
        this.currentScene = this.sceneManager.get(sceneType);
        System.out.println("Cambiando a escena: " + sceneType + " -> " + this.currentScene);

        // Inicializar y setear el estado
        this.currentScene.show();
        this.setGameState(GameState.PLAYING);
    }


	public void setGameState(GameState state) {
		this.currentState = state;
	}
	
	public GameScene getCurrentScene() {
		return this.currentScene;
	}
}
