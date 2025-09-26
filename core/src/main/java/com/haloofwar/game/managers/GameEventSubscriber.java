package com.haloofwar.game.managers;

import java.util.HashSet;
import java.util.Set;

import com.haloofwar.common.enums.GameState;
import com.haloofwar.common.enums.LevelSceneType;
import com.haloofwar.common.enums.SoundType;
import com.haloofwar.common.managers.SceneManager;
import com.haloofwar.engine.components.TransformComponent;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.ChangeCurrentPlayerEvent;
import com.haloofwar.engine.events.ChangeSceneEvent;
import com.haloofwar.engine.events.EnterLevelEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.EventListenerManager;
import com.haloofwar.engine.events.GameStateEvent;
import com.haloofwar.engine.events.LevelCompletedEvent;
import com.haloofwar.engine.events.NewPlayerEvent;
import com.haloofwar.engine.events.PeacefulEvent;
import com.haloofwar.engine.events.PlayMusicEvent;
import com.haloofwar.engine.events.PlaySoundEvent;
import com.haloofwar.engine.events.PlayerDiedEvent;
import com.haloofwar.engine.events.StopMusicEvent;
import com.haloofwar.engine.events.StopSoundsEvent;
import com.haloofwar.engine.interfaces.Disposable;
import com.haloofwar.game.cutscenes.LevelCompletedScene;
import com.haloofwar.interfaces.Scene;

public class GameEventSubscriber implements Disposable {
	private final EventListenerManager listenerManager = new EventListenerManager();
    private final EventBus bus;
    private final GameFlowManager flowManager;
    private TransformComponent playerTransform;
    private float lastPlayerXAtLobby = 0f;
    private float lastPlayerYAtLobby = 0f;
    private final Set<LevelSceneType> completedLevels;
    private final LevelCompletedScene completedScene;
    private final SceneManager sceneManager;

    public GameEventSubscriber(GameFlowManager flowManager, EventBus bus, SceneManager sceneManager,
                               Entity player, LevelCompletedScene completedScene,
                               Set<LevelSceneType> initialCompletedLevels) {
        this.flowManager = flowManager;
        this.bus = bus;
        this.playerTransform = player.getComponent(TransformComponent.class);

        this.completedScene = completedScene;
        this.sceneManager = sceneManager;

        this.completedLevels = (initialCompletedLevels != null) ? initialCompletedLevels : new HashSet<>();
        this.subscribeEvents();
    }

    private void subscribeEvents() {
        this.listenerManager.add(this.bus, PlayerDiedEvent.class, this::onPlayerDied);
        this.listenerManager.add(this.bus, EnterLevelEvent.class, this::onEnterLevel);
        this.listenerManager.add(this.bus, LevelCompletedEvent.class, this::onLevelCompleted);
        this.listenerManager.add(this.bus, ChangeSceneEvent.class, this::onChangeScene);
        this.listenerManager.add(this.bus, NewPlayerEvent.class, this::onNewPlayer);
        this.listenerManager.add(this.bus, GameStateEvent.class, this::onNewGameState);
        this.bus.publish(new GameStateEvent(this.flowManager.getCurrentState()));
    }

    private void onNewPlayer(NewPlayerEvent event) {
    	this.flowManager.changePlayer(event);
    	this.playerTransform = event.player.getComponent(TransformComponent.class);
    }

    private boolean ignoreIfPaused(Object event) {
        if (event instanceof GameStateEvent) {
        	return false;
        }
        
        return flowManager.getCurrentState() == GameState.PAUSED;
    }

    private void onPlayerDied(PlayerDiedEvent event) {
        if (ignoreIfPaused(event)) {
        	return;
        }
        
        this.bus.publish(new StopSoundsEvent());
        this.bus.publish(new StopMusicEvent());
        this.bus.publish(new PlaySoundEvent(SoundType.GAME_OVER));
        this.bus.publish(new GameStateEvent(GameState.GAME_OVER));
    }

    private void onNewGameState(GameStateEvent event) {
        flowManager.setGameState(event.getState());
    }

    private void onLevelCompleted(LevelCompletedEvent event) {
        bus.publish(new StopMusicEvent());
        bus.publish(new PlayMusicEvent(this.completedScene.getMusic()));
        if (ignoreIfPaused(event)) return;
        if (event.getLevelType() != null) completedLevels.add(event.getLevelType());

        if (playerTransform != null) {
            this.playerTransform.x = lastPlayerXAtLobby;
            this.playerTransform.y = lastPlayerYAtLobby;
        }

        this.bus.publish(new PeacefulEvent(!this.completedScene.isLevel()));
        this.flowManager.changeScene(completedScene);
    }

    private void onEnterLevel(EnterLevelEvent event) {
        if (this.ignoreIfPaused(event)) {
        	return;
        }
        
        if (event == null || event.type == null || completedLevels.contains(event.type)) {
        	return;
        }

        this.lastPlayerXAtLobby = this.playerTransform.x;
        this.lastPlayerYAtLobby = this.playerTransform.y;

        final Scene targetScene = this.sceneManager.get(event.type);
        this.bus.publish(new PeacefulEvent(!targetScene.isLevel()));
        
        if (targetScene != null) {
        	this.onChangeScene(new ChangeSceneEvent(event.type));
        }
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

	@Override
	public void dispose() {
		this.listenerManager.clear();
	}
}
