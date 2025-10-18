package com.haloofwar.common.context;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.common.enumerators.PlayerType;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.ChangeTargetEvent;
import com.haloofwar.engine.events.CheckPlayersAliveEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.EventListenerManager;
import com.haloofwar.engine.events.NewEntityEvent;
import com.haloofwar.engine.events.PlayerDiedEvent;
import com.haloofwar.engine.events.RemoveEntitiesToChangeSceneEvent;
import com.haloofwar.engine.events.RemoveEntityEvent;
import com.haloofwar.engine.events.RespawnPlayerEventOnline;
import com.haloofwar.engine.events.online.SwitchToSpectatorEventOnline;
import com.haloofwar.engine.interfaces.Disposable;
import com.haloofwar.engine.interfaces.Renderable;
import com.haloofwar.game.components.CollectComponent;
import com.haloofwar.game.components.EnemyComponent;
import com.haloofwar.game.components.HealthComponent;
import com.haloofwar.game.components.PlayerComponent;
import com.haloofwar.game.components.PowerUpComponent;
import com.haloofwar.game.components.WallComponent;
import com.haloofwar.game.factories.SystemFactory;
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
    
    public GameplayContext(final SpriteBatch batch, final TextureManager texture, final EventBus gameplayBus) {
        this.gameplayBus = gameplayBus;
        this.batch = batch;
        this.texture = texture;
        this.systems = SystemFactory.createGameplaySystems(this.batch, this.texture, this.gameplayBus, this);
        this.initializeEvents();
    }

    private void initializeEvents() {
        if (this.gameplayBus != null) {
            this.listenerManager.add(this.gameplayBus, NewEntityEvent.class, this::addEntity);
            this.listenerManager.add(this.gameplayBus, RemoveEntityEvent.class, this::removeEntity);
            this.listenerManager.add(this.gameplayBus, RemoveEntitiesToChangeSceneEvent.class, this::removeEntitiesForChangeScene);
            this.listenerManager.add(this.gameplayBus, CheckPlayersAliveEvent.class, this::checkPlayers);
            this.listenerManager.add(this.gameplayBus, SwitchToSpectatorEventOnline.class, this::switchTarget);
            
        } else {
            System.out.println("No se pueden inicializar los eventos porque el GameplayBus es nulo... | GameplayContext");
        }
    }
    
    private void switchTarget(SwitchToSpectatorEventOnline event) { // no deberia manejarlo desde este evento pero bueno
    	Entity newTarget;
    	
    	if(event.type.equals(PlayerType.KRATOS)) {
    		newTarget = this.masterchief;
    	} else {
    		newTarget = this.kratos;
    	}
    	
    	this.gameplayBus.publish(new ChangeTargetEvent(newTarget));
    }
    
    
    private void checkPlayers(CheckPlayersAliveEvent event) {
    	this.checkPlayer(this.kratos);
    	this.checkPlayer(this.masterchief);
    }
    
    private void checkPlayer(Entity entity) {
    	if(!entity.hasComponent(PlayerComponent.class)) {
    		System.out.println("No es un jugador, no se puede validar la existencia... | GameplayContext");
    		return;
    	}
    	
    	HealthComponent hc = entity.getComponent(HealthComponent.class);
    	
    	if(hc.reset()) {
    		PlayerComponent pc = entity.getComponent(PlayerComponent.class);
    		this.gameplayBus.publish(new NewEntityEvent(entity));
    		this.gameplayBus.publish(new RespawnPlayerEventOnline(pc.type));
    	}
    }
    
    private void removeEntitiesForChangeScene(RemoveEntitiesToChangeSceneEvent event) {
    	this.systems.removeEntityByComponent(EnemyComponent.class);
    	this.systems.removeEntityByComponent(WallComponent.class);
    	this.systems.removeEntityByComponent(CollectComponent.class);
    	this.systems.removeEntityByComponent(PowerUpComponent.class);
    }

    public void initializePlayer(final Entity player) {
    	if(player == null) {
    		System.out.println("No se ha podido inicializar al jugador porque es nulo... | GameplayContext");
    		return;
    	}
    	
        if (player.hasComponent(PlayerComponent.class)) {
        	if(this.currentPlayer == null) {
        		this.currentPlayer = player;
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
    
    public Entity getPlayerByType(final PlayerType TYPE) {
    	Entity entityFound = null;
    	
		if(TYPE == null) {
			System.out.println("No se ha podido obtener al jugador porque el tipo es nulo... | GameplayContext");
			return entityFound;
		}
		
		if(TYPE.equals(PlayerType.KRATOS)) {
			if(this.kratos == null) {
				System.out.println("No se ha podido obtener a Kratos porque es nulo... | GameplayContext");
			} else {
				entityFound = this.kratos;
			}
		} else if(TYPE.equals(PlayerType.MASTER_CHIEF)) {
			if(this.masterchief == null) {
				System.out.println("No se ha podido obtener a Master Chief porque es nulo... | GameplayContext");
			} else {
				entityFound = this.masterchief;
			}
		} else {
			System.out.println("No se ha podido obtener al jugador porque el tipo es inválido... | GameplayContext");
		}
    	
		return entityFound;
    }
    
    public Entity generateTarget() {
    	if(!this.playerIsAlive(PlayerType.KRATOS) && !this.playerIsAlive(PlayerType.MASTER_CHIEF)) {
    		return null;
    	} else if(this.playerIsAlive(PlayerType.KRATOS) && !this.playerIsAlive(PlayerType.MASTER_CHIEF)) {
    		return this.kratos;
    	} else if(!this.playerIsAlive(PlayerType.KRATOS) && this.playerIsAlive(PlayerType.MASTER_CHIEF)) {
    		return this.masterchief;
    	} else {
    		return this.getPlayerByType(PlayerType.generate());
    	}
    }
    
    private boolean playerIsAlive(PlayerType type) {
    	Entity entity = this.getPlayerByType(type);
    	HealthComponent hc = entity.getComponent(HealthComponent.class);
    	
    	return hc.alive;
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
    	if(event.entity.hasComponent(PlayerComponent.class)) {
    		this.gameplayBus.publish(new PlayerDiedEvent(event.entity));
    	}
    	
        this.systems.removeEntity(event.entity);
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
