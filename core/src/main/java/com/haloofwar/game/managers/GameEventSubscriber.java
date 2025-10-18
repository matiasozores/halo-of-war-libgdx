package com.haloofwar.game.managers;

import java.util.HashSet;
import java.util.Set;

import com.haloofwar.common.enumerators.GameState;
import com.haloofwar.common.enumerators.LevelSceneType;
import com.haloofwar.common.enumerators.SoundType;
import com.haloofwar.common.managers.SceneManager;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.ChangeCurrentPlayerEvent;
import com.haloofwar.engine.events.ChangeSceneEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.EventListenerManager;
import com.haloofwar.engine.events.GameOverEvent;
import com.haloofwar.engine.events.GameStateEvent;
import com.haloofwar.engine.events.LevelCompletedEvent;
import com.haloofwar.engine.events.LevelEnterEvent;
import com.haloofwar.engine.events.PeacefulEvent;
import com.haloofwar.engine.events.PlayMusicEvent;
import com.haloofwar.engine.events.PlaySoundEvent;
import com.haloofwar.engine.events.PortalChangeStateEvent;
import com.haloofwar.engine.events.StopMusicEvent;
import com.haloofwar.engine.events.StopSoundsEvent;
import com.haloofwar.engine.events.UnlockLevelEvent;
import com.haloofwar.engine.interfaces.Disposable;
import com.haloofwar.game.components.TransformComponent;
import com.haloofwar.game.cutscenes.LevelCompletedScene;
import com.haloofwar.game.cutscenes.LevelGameOverScene;
import com.haloofwar.interfaces.Scene;

public class GameEventSubscriber implements Disposable {
	private final EventListenerManager listenerManager = new EventListenerManager();
    private final EventBus bus;
    private final GameFlowManager flowManager;
    private TransformComponent kratosTransform;
    private TransformComponent masterchiefTransform;
    private float lastPlayerXAtLobby = 0f;
    private float lastPlayerYAtLobby = 0f;
    private final Set<LevelSceneType> completedLevels;
    private final Set<LevelSceneType> lockedLevels;
    private final LevelCompletedScene completedScene;
    private final LevelGameOverScene gameOverScene;
    private final SceneManager sceneManager;

    public GameEventSubscriber(GameFlowManager flowManager, EventBus bus, SceneManager sceneManager,
                               Entity kratos, Entity masterchief, LevelCompletedScene completedScene, LevelGameOverScene gameOverScene,
                               Set<LevelSceneType> initialCompletedLevels, Set<LevelSceneType> lockedLevels) {
        this.flowManager = flowManager;
        this.bus = bus;
        this.kratosTransform = kratos.getComponent(TransformComponent.class);
        this.masterchiefTransform = masterchief.getComponent(TransformComponent.class);
        
        this.completedScene = completedScene;
        this.gameOverScene = gameOverScene;
        this.sceneManager = sceneManager;

        this.completedLevels = (initialCompletedLevels != null) ? initialCompletedLevels : new HashSet<>();
        this.subscribeEvents();
        this.lockedLevels = lockedLevels;
    }
    
    private void subscribeEvents() {

        this.listenerManager.add(this.bus, ChangeSceneEvent.class, this::onChangeScene);
        this.listenerManager.add(this.bus, GameStateEvent.class, this::onNewGameState);
        this.listenerManager.add(this.bus, LevelEnterEvent.class, this::onEnterLevel);
        this.listenerManager.add(this.bus, GameOverEvent.class, this::onPlayerDied);
        this.listenerManager.add(this.bus, LevelCompletedEvent.class, this::onLevelCompleted);
        this.listenerManager.add(this.bus, UnlockLevelEvent.class, this::unlockLevel);
        this.bus.publish(new GameStateEvent(this.flowManager.getCurrentState()));
    }


    private void unlockLevel(UnlockLevelEvent event) {
    	if(this.lockedLevels.contains(event.type)) {
    		this.lockedLevels.remove(event.type);
    	}
    }

    private boolean ignoreIfPaused(Object event) {
        if (event instanceof GameStateEvent) {
        	return false;
        }
        
        return flowManager.getCurrentState() == GameState.PAUSED;
    }
    
    private void onEnterLevel(LevelEnterEvent event) {
		if (this.ignoreIfPaused(event)) {
        	return;
        }
        
        if (event == null || event.type == null || completedLevels.contains(event.type)) {
        	return;
        }

        this.lastPlayerXAtLobby = this.kratosTransform.x;
        this.lastPlayerYAtLobby = this.kratosTransform.y;

        final Scene targetScene = this.sceneManager.get(event.type);
        this.bus.publish(new PeacefulEvent(!targetScene.isLevel()));
        
        if (targetScene != null) {
        	this.onChangeScene(new ChangeSceneEvent(event.type));
        }
    }

    private void onPlayerDied(GameOverEvent event) {
        if (this.ignoreIfPaused(event)) {
        	return;
        }
        
        this.bus.publish(new StopSoundsEvent());
        this.bus.publish(new StopMusicEvent());
        this.bus.publish(new PlaySoundEvent(SoundType.GAME_OVER));
        this.bus.publish(new PeacefulEvent(!this.gameOverScene.isLevel())); // ojo con este
        this.flowManager.changeScene(this.gameOverScene);
    }

    private void onNewGameState(GameStateEvent event) {
        flowManager.setGameState(event.getState());
    }

    private void onLevelCompleted(LevelCompletedEvent event) {
        bus.publish(new StopMusicEvent());
        bus.publish(new PlayMusicEvent(this.completedScene.getMusic()));
        if (ignoreIfPaused(event)) return;
        if (event.getLevelType() != null) {
        	completedLevels.add(event.getLevelType());
        }
        
    	this.bus.publish(new UnlockLevelEvent(event.getLevelType().getNextLevel()));
    	this.bus.publish(new PortalChangeStateEvent(event.getLevelType()));
    	
        if (kratosTransform != null) {
            this.kratosTransform.x = lastPlayerXAtLobby;
            this.kratosTransform.y = lastPlayerYAtLobby;
        }
        
        if(this.masterchiefTransform != null) {
			this.masterchiefTransform.x = lastPlayerXAtLobby + 50; 
			this.masterchiefTransform.y = lastPlayerYAtLobby;
        }

        this.bus.publish(new PeacefulEvent(!this.completedScene.isLevel()));
        this.bus.publish(new PortalChangeStateEvent(event.getLevelType()));
        this.flowManager.changeScene(completedScene);
    }

    private void onChangeScene(ChangeSceneEvent event) {
        if (this.ignoreIfPaused(event)) {
        	return;
        }
        
        this.bus.publish(new PeacefulEvent(!event.getNextScene().isLevel()));
        
        final Scene nextScene = this.sceneManager.get(event.getNextScene());
        
        this.flowManager.changeScene(nextScene);
        this.bus.publish(new ChangeCurrentPlayerEvent());
        this.bus.publish(new ChangeCurrentPlayerEvent());
        this.bus.publish(new StopMusicEvent());
        this.bus.publish(new PlayMusicEvent(event.getNextScene().getScene().getMusic()));
    }

    public Set<LevelSceneType> getCompletedLevels() {
        return this.completedLevels;
    }
    
    public Set<LevelSceneType> getLockedLevels() {
		return this.lockedLevels;
	}

	@Override
	public void dispose() {
		this.listenerManager.clear();
	}
}
