package com.haloofwar.game.managers;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.common.enumerators.GameState;
import com.haloofwar.common.enumerators.InventoryItemStatus;
import com.haloofwar.common.enumerators.LevelSceneType;
import com.haloofwar.common.enumerators.ObjectType;
import com.haloofwar.common.enumerators.PlayerType;
import com.haloofwar.common.managers.SceneManager;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.ChangeCurrentPlayerEvent;
import com.haloofwar.engine.events.ChangeSceneEvent;
import com.haloofwar.engine.events.CheckPlayersAliveEvent;
import com.haloofwar.engine.events.EnterLevelEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.EventListenerManager;
import com.haloofwar.engine.events.GameOverEvent;
import com.haloofwar.engine.events.GameStateEvent;
import com.haloofwar.engine.events.LevelCompletedEvent;
import com.haloofwar.engine.events.PeacefulEvent;
import com.haloofwar.engine.events.PlayMusicEvent;
import com.haloofwar.engine.events.PlayerDiedEvent;
import com.haloofwar.engine.events.RemoveEntitiesToChangeSceneEvent;
import com.haloofwar.engine.events.StopMusicEvent;
import com.haloofwar.engine.events.UnlockLevelEvent;
import com.haloofwar.engine.events.UpdateInventoryEvent;
import com.haloofwar.engine.events.online.EnterLevelEventOnline;
import com.haloofwar.engine.events.online.GameOverEventOnline;
import com.haloofwar.engine.events.online.LevelCompletedEventOnline;
import com.haloofwar.engine.events.online.SwitchToSpectatorEventOnline;
import com.haloofwar.engine.events.online.UpdateInventoryEventOnline;
import com.haloofwar.engine.interfaces.Disposable;
import com.haloofwar.game.components.PlayerComponent;
import com.haloofwar.game.components.TransformComponent;
import com.haloofwar.game.cutscenes.LevelCompletedScene;
import com.haloofwar.game.cutscenes.LevelGameOverScene;
import com.haloofwar.game.factories.ObjectFactory;
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
    private final TextureManager texture;
    private int playersDeadCounter = 0;
    
    public GameEventSubscriber(
    	GameFlowManager flowManager, 
    	EventBus bus, 
    	SceneManager sceneManager,
    	Entity kratos, 
    	Entity masterchief, 
    	LevelCompletedScene completedScene,
    	LevelGameOverScene gameOverScene,
    	TextureManager texture
    ) {
        this.flowManager = flowManager;
        this.bus = bus;
        this.kratosTransform = kratos.getComponent(TransformComponent.class);
        this.masterchiefTransform = masterchief.getComponent(TransformComponent.class);
        
        this.completedScene = completedScene;
        this.gameOverScene = gameOverScene;
        this.sceneManager = sceneManager;

        this.completedLevels = new HashSet<LevelSceneType>();
        this.lockedLevels = LevelSceneType.getAllLockedLevels();
        this.subscribeEvents();
        
        this.texture = texture;
    }

    private void subscribeEvents() {
        this.listenerManager.add(this.bus, PlayerDiedEvent.class, this::onPlayerDied);
        this.listenerManager.add(this.bus, EnterLevelEvent.class, this::onEnterLevel);
        this.listenerManager.add(this.bus, LevelCompletedEvent.class, this::onLevelCompleted);
        this.listenerManager.add(this.bus, ChangeSceneEvent.class, this::onChangeScene);
        this.listenerManager.add(this.bus, GameStateEvent.class, this::onNewGameState);
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

    private void onPlayerDied(PlayerDiedEvent event) {
    	// se verifica si murieron los dos jugadores, si es asi game over sino uno pasa a espectear al otro
    	this.playersDeadCounter++;
    	
    	if(this.playersDeadCounter == 2) {
            this.bus.publish(new PeacefulEvent(!this.gameOverScene.isLevel()));
            this.flowManager.changeScene(this.gameOverScene);
            this.bus.publish(new GameOverEvent());
    		this.bus.publish(new GameOverEventOnline());
    		this.playersDeadCounter = 0;
    		this.bus.publish(new RemoveEntitiesToChangeSceneEvent()); // erste es elcasusante de que la patnalla de vicotria se muestrw aunque no se haya ganado el niuvel
    	} else {
    		final PlayerComponent pc = event.player.getComponent(PlayerComponent.class);
    		this.bus.publish(new SwitchToSpectatorEventOnline(pc.type));
    	}

    }

    private void onNewGameState(GameStateEvent event) {
        this.flowManager.setGameState(event.getState());
        
        if(event.getState().equals(GameState.GAME_OVER)) {
        	this.bus.publish(new GameOverEventOnline());
        }
    }

    private void onLevelCompleted(LevelCompletedEvent event) {
        bus.publish(new StopMusicEvent());
        bus.publish(new PlayMusicEvent(this.completedScene.getMusic()));
        if (ignoreIfPaused(event)) return;
        if (event.getLevelType() != null) completedLevels.add(event.getLevelType());

        if (kratosTransform != null) {
            this.kratosTransform.x = lastPlayerXAtLobby;
            this.kratosTransform.y = lastPlayerYAtLobby;
        }
        
        if(this.masterchiefTransform != null) {
			this.masterchiefTransform.x = lastPlayerXAtLobby + 50; 
			this.masterchiefTransform.y = lastPlayerYAtLobby;
        }
    	this.bus.publish(new RemoveEntitiesToChangeSceneEvent());
        this.bus.publish(new PeacefulEvent(!this.completedScene.isLevel()));
        this.giveCoins(event.getLevelType());
        

        this.flowManager.changeScene(completedScene);
        
        this.playersDeadCounter = 0;
        this.bus.publish(new LevelCompletedEventOnline(event.getLevelType()));
    }
    
    private void giveCoins(LevelSceneType type) {
    	  Entity coinToKratos = ObjectFactory.createItem(new Rectangle(0,0,0,0), ObjectType.MONEDA_DE_ORO, this.texture);
    	  Entity coinToMasterchief = ObjectFactory.createItem(new Rectangle(0,0,0,0), ObjectType.MONEDA_DE_ORO, this.texture);
    	  
    	  this.bus.publish(new UpdateInventoryEvent(this.kratosTransform.identifier, coinToKratos, type.getGoldCoins(), InventoryItemStatus.ADD));
    	  this.bus.publish(new UpdateInventoryEvent(this.masterchiefTransform.identifier, coinToMasterchief, type.getGoldCoins(), InventoryItemStatus.ADD));
    	  
          this.bus.publish(new UpdateInventoryEventOnline(this.kratosTransform.identifier, PlayerType.KRATOS, ObjectType.MONEDA_DE_ORO, type.getGoldCoins(), InventoryItemStatus.ADD));
          this.bus.publish(new UpdateInventoryEventOnline(this.masterchiefTransform.identifier, PlayerType.MASTER_CHIEF, ObjectType.MONEDA_DE_ORO, type.getGoldCoins(), InventoryItemStatus.ADD));
    }

    private void onEnterLevel(EnterLevelEvent event) {
    	Gdx.app.postRunnable(() -> {
    		if (this.ignoreIfPaused(event)) {
            	return;
            }
            
            if (event == null || event.type == null || completedLevels.contains(event.type) || this.lockedLevels.contains(event.type)) {
            	return;
            }

            this.lastPlayerXAtLobby = this.kratosTransform.x;
            this.lastPlayerYAtLobby = this.kratosTransform.y;
            
        	this.bus.publish(new RemoveEntitiesToChangeSceneEvent());
            final Scene targetScene = this.sceneManager.get(event.type);
            targetScene.reset();
            
            this.bus.publish(new PeacefulEvent(!targetScene.isLevel()));
            
            
            if (targetScene != null) {
            	this.onChangeScene(new ChangeSceneEvent(event.type));
            }
            
            this.bus.publish(new EnterLevelEventOnline(event.type));
    	});
    }

    private void onChangeScene(ChangeSceneEvent event) {
        this.bus.publish(new CheckPlayersAliveEvent());
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
