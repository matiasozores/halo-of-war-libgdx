package com.haloofwar.game.managers;

import java.util.Set;

import com.haloofwar.common.context.GameplayContext;
import com.haloofwar.common.enumerators.GameState;
import com.haloofwar.common.enumerators.LevelSceneType;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.events.EventListenerManager;
import com.haloofwar.engine.events.GameStateEvent;
import com.haloofwar.game.components.TransformComponent;
import com.haloofwar.game.world.World;
import com.haloofwar.game.world.WorldCollisionInitializer;
import com.haloofwar.interfaces.Scene;

public class GameFlowManager {

    private Scene currentScene;
    private GameState currentState;
    private final GameplayContext GAMEPLAY_CONTEXT;
    private final TextureManager texture;
    private TransformComponent kratosTransform;
    private TransformComponent masterchiefTransform;
    private final EventListenerManager listenerManager = new EventListenerManager();
    private final Set<LevelSceneType> lockedLevels;
    
    public GameFlowManager(TextureManager texture, GameplayContext GAMEPLAY_CONTEXT, Set<LevelSceneType> lockedLevels) {
    	this.GAMEPLAY_CONTEXT = GAMEPLAY_CONTEXT;
        this.kratosTransform = this.GAMEPLAY_CONTEXT.getKratos().getComponent(TransformComponent.class);
        this.masterchiefTransform = this.GAMEPLAY_CONTEXT.getMasterchief().getComponent(TransformComponent.class);
        
        this.currentState = GameState.PLAYING;
        this.listenerManager.add(GAMEPLAY_CONTEXT.getBus(), GameStateEvent.class, this::onGameStateChange);
        
        this.texture = texture;
        this.lockedLevels = lockedLevels;
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
            	this.kratosTransform = this.GAMEPLAY_CONTEXT.getKratos().getComponent(TransformComponent.class);
            	this.masterchiefTransform = this.GAMEPLAY_CONTEXT.getMasterchief().getComponent(TransformComponent.class);
            	this.playerReposition(currentScene.getWorld());
            	WorldCollisionInitializer.initializeMapColliders(this.lockedLevels, this.currentScene.getWorld().getMap(), this.texture, this.GAMEPLAY_CONTEXT.getBus());
            }
        }

        this.currentState = GameState.PLAYING;
    }

    public void playerReposition(World world) {
        float x = world.getMap().getMetaData().getxSpawnPoint();
        float y = world.getMap().getMetaData().getySpawnPoint();
        
        this.kratosTransform.x = x;
        this.kratosTransform.y = y;
        
        this.masterchiefTransform.x = x + 60; 
        this.masterchiefTransform.y = y;
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
    
    public void dispose() {
    	this.listenerManager.clear();
    }
}
