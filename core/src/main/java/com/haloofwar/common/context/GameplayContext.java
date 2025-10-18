package com.haloofwar.common.context;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.common.enumerators.PlayerType;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.cameras.GameWorldCamera;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.EventListenerManager;
import com.haloofwar.engine.events.NewEntityEvent;
import com.haloofwar.engine.events.RemoveEntityByIdentifierEvent;
import com.haloofwar.engine.events.RemoveEntityEvent;
import com.haloofwar.engine.events.RespawnPlayerEvent;
import com.haloofwar.engine.events.SwitchToSpectatorEvent;
import com.haloofwar.engine.interfaces.Disposable;
import com.haloofwar.engine.interfaces.Renderable;
import com.haloofwar.game.components.PlayerComponent;
import com.haloofwar.game.factories.SystemFactory;
import com.haloofwar.game.systems.PlayerSystem;
import com.haloofwar.game.systems.SystemCollection;
import com.haloofwar.interfaces.Updatable;

public class GameplayContext {
    private SystemCollection systems;
    private final EventListenerManager listenerManager = new EventListenerManager();
    
    private Entity currentPlayer;
    private Entity kratos;
    private Entity masterchief;
    
    private final EventBus gameplayBus;
    private final SpriteBatch batch;
    private final TextureManager texture;
    private final GameWorldCamera camera;
    
    public GameplayContext(final SpriteBatch batch, final TextureManager texture, final EventBus gameplayBus, final GameWorldCamera camera) {
        this.gameplayBus = gameplayBus;
        this.batch = batch;
        this.texture = texture;
        this.systems = SystemFactory.createGameplaySystems(this.batch, this.texture, this.gameplayBus, this);
        this.camera = camera;
        this.initializeEvents();
    }

    private void initializeEvents() {
        if (this.gameplayBus != null) {
            this.listenerManager.add(this.gameplayBus, NewEntityEvent.class, this::addEntity);
            this.listenerManager.add(this.gameplayBus, RemoveEntityEvent.class, this::removeEntity);
            this.listenerManager.add(this.gameplayBus, RemoveEntityByIdentifierEvent.class, this::removeEntityByIdentifier);
            this.listenerManager.add(this.gameplayBus, SwitchToSpectatorEvent.class, this::switchToSpectator);
            this.listenerManager.add(this.gameplayBus, RespawnPlayerEvent.class, this::respawnPlayer);
        } else {
            System.out.println("No se pueden inicializar los eventos porque el GameplayBus es nulo... | GameplayContext");
        }
    }
    
    private void respawnPlayer(RespawnPlayerEvent event) {
    	Entity target = this.getPlayerByType(event.type);
    	this.gameplayBus.publish(new NewEntityEvent(target));
    }
    
    private void switchToSpectator(SwitchToSpectatorEvent event) {
    	Entity target = this.currentPlayer.equals(this.kratos) ? this.masterchief : this.kratos;
    	this.camera.changeTarget(target);
    }
    
    public void initializePlayer(final Entity player) {
    	if(player == null) {
    		System.out.println("No se ha podido inicializar al jugador porque es nulo... | GameplayContext");
    		return;
    	}
    	
        if (player.hasComponent(PlayerComponent.class)) {
        	if(this.currentPlayer == null) {
        		this.currentPlayer = player;
        		SystemFactory.registerSystem(this.systems, new PlayerSystem(player, this.gameplayBus, this.texture, this));
        	}
        	
        	this.addEntity(new NewEntityEvent(player));
        	
        	final PlayerType TYPE = player.getComponent(PlayerComponent.class).type;
        	
        	if(TYPE.equals(PlayerType.KRATOS)) {
        		this.kratos = player;
        	} else {
        		this.masterchief = player;
        	}
        }
    }
    
    public void initializePlayers(final Entity player1, final Entity player2) {
    	this.initializePlayer(player1);
    	this.initializePlayer(player2);
    }

    public void update(float delta) {
        for (Updatable system : this.systems.getUPDATE_SYSTEMS()) {
            system.update(delta);
        }
    }

    public void render() {
        for (Renderable system : this.systems.getRENDER_SYSTEMS()) {
            system.render();
        }
    }

    public void addEntity(final NewEntityEvent event) {
        this.systems.addEntity(event.entity);
    }

    public void removeEntity(final RemoveEntityEvent event) {
        this.systems.removeEntity(event.entity);
    }
    
    public void removeEntityByIdentifier(final RemoveEntityByIdentifierEvent event) {
    	this.systems.removeEntityByIdentifier(event.identifier);
    }

    public Entity getCurrentPlayer() {
        return this.currentPlayer;
    }
    
    public Entity getKratos() {
		return this.kratos;
	}
    
    public Entity getMasterchief() {
		return this.masterchief;
	}

    public EventBus getBus() {
        return this.gameplayBus;
    }
    
    public Entity getPlayerByType(PlayerType type) {
    	if(type.equals(PlayerType.KRATOS)) {
    		return this.kratos;
    	} else {
    		return this.masterchief;
    	}
    }
    
    public void dispose() {
    	this.listenerManager.clear();
        for (Disposable system : this.systems.getDISPOSABLE_SYSTEMS()) {
            system.dispose();
        }
        
        this.currentPlayer = null;
        this.kratos = null;
        this.masterchief = null;
        
        if (this.gameplayBus != null) {
            this.gameplayBus.clear();
            this.systems = SystemFactory.createGameplaySystems(this.batch, this.texture, this.gameplayBus, this);
            this.initializeEvents();
        }
    }
}
