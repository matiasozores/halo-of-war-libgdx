package com.haloofwar.game.managers;

import com.haloofwar.common.context.GameplayContext;
import com.haloofwar.common.enums.GameState;
import com.haloofwar.engine.components.TransformComponent;
import com.haloofwar.engine.events.GameStateEvent;
import com.haloofwar.engine.events.NewPlayerEvent;
import com.haloofwar.game.world.World;
import com.haloofwar.interfaces.Scene;

public class GameFlowManager {

    private Scene currentScene;
    private GameState currentState;
    private final GameplayContext GAMEPLAY_CONTEXT;
    private TransformComponent playerTransform;
    
    public GameFlowManager(GameplayContext GAMEPLAY_CONTEXT) {
    	this.GAMEPLAY_CONTEXT = GAMEPLAY_CONTEXT;
        this.playerTransform = this.GAMEPLAY_CONTEXT.getCurrentPlayer().getComponent(TransformComponent.class);
        this.currentState = GameState.PLAYING;
        GAMEPLAY_CONTEXT.getBus().subscribe(GameStateEvent.class, this::onGameStateChange);
    }

    private void onGameStateChange(GameStateEvent event) {
        this.currentState = event.getState();
    }

    public void update(float delta) {
        if (currentScene != null && (currentState == GameState.PLAYING || currentState == GameState.WAITING)) {
            currentScene.update(delta);
        }
    }

    public void render(float delta) {
        if (currentScene != null) currentScene.render(delta);
    }

    public void changeScene(Scene newScene) {
        if (currentScene != null) {
            currentScene.dispose();
            currentScene.hide();
        }
        currentScene = newScene;
        if (currentScene != null) {
            currentScene.show();
            currentScene.reset();
            currentScene.reconfigureCamera();
            if (currentScene.getWorld() != null) {
            	// por las dudas
            	this.playerTransform = this.GAMEPLAY_CONTEXT.getCurrentPlayer().getComponent(TransformComponent.class);
            	this.playerReposition(currentScene.getWorld());
            }
        }

        this.currentState = GameState.PLAYING;
    }
    
    public void changePlayer(NewPlayerEvent event) {
    	this.playerTransform = event.player.getComponent(TransformComponent.class);
    }

    public void playerReposition(World world) {
        float x = world.getMap().getMetaData().getxSpawnPoint();
        float y = world.getMap().getMetaData().getySpawnPoint();
        
        this.playerTransform.x = x;
        this.playerTransform.y = y;
    }
    
    public void setGameState(GameState state) {
    	this.currentState = state;
    }

    public GameState getCurrentState() {
        return this.currentState;
    }
    
    public Scene getCurrentScene() {
		return this.currentScene;
	}
}
