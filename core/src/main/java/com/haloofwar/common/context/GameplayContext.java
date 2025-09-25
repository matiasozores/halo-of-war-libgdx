package com.haloofwar.common.context;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.common.enums.PlayerType;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.cameras.GameWorldCamera;
import com.haloofwar.engine.components.TransformComponent;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.ChangeCurrentPlayerEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.NewEntityEvent;
import com.haloofwar.engine.events.NewPlayerEvent;
import com.haloofwar.engine.events.RemoveEntityEvent;
import com.haloofwar.engine.interfaces.Disposable;
import com.haloofwar.engine.interfaces.Renderable;
import com.haloofwar.engine.systems.SystemCollection;
import com.haloofwar.game.components.PlayerComponent;
import com.haloofwar.game.factories.SystemFactory;
import com.haloofwar.game.systems.PlayerSystem;
import com.haloofwar.interfaces.Updatable;

public class GameplayContext {
    private SystemCollection systems;
    
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
        this.camera = camera;
        this.systems = SystemFactory.createGameplaySystems(this.batch, this.texture, this.gameplayBus);
        this.initializeEvents();
    }

    private void initializeEvents() {
        if (this.gameplayBus != null) {
            this.gameplayBus.subscribe(NewEntityEvent.class, this::addEntity);
            this.gameplayBus.subscribe(RemoveEntityEvent.class, this::removeEntity);
            this.gameplayBus.subscribe(ChangeCurrentPlayerEvent.class, this::onChangeCurrentPlayer);
        } else {
            System.out.println("No se pueden inicializar los eventos porque el GameplayBus es nulo... | GameplayContext");
        }
    }
    
    private void onChangeCurrentPlayer(ChangeCurrentPlayerEvent event) {
        if (this.masterchief == null || this.kratos == null) {
            System.out.println("No se puede cambiar de jugador porque Kratos o Master Chief es nulo... | GameplayContext");
            return;
        }

        final PlayerComponent playerComp = this.currentPlayer.getComponent(PlayerComponent.class);

        this.gameplayBus.publish(new RemoveEntityEvent(this.currentPlayer));

        Entity nextPlayer;
        switch (playerComp.type) {
            case KRATOS -> nextPlayer = this.masterchief;
            case MASTER_CHIEF -> nextPlayer = this.kratos;
            default -> {
                System.out.println("Ha ocurrido un problema al intentar cambiar de personaje... | GameplayContext");
                nextPlayer = this.kratos;
            }
        }

        this.repositionPlayer(nextPlayer);
        this.currentPlayer = nextPlayer;

        this.gameplayBus.publish(new NewPlayerEvent(this.currentPlayer));
        this.gameplayBus.publish(new NewEntityEvent(this.currentPlayer));
        this.camera.changeTarget(this.currentPlayer);
    }


    private void repositionPlayer(final Entity TARGET) {
    	final TransformComponent TRANSFORM_TARGET = TARGET.getComponent(TransformComponent.class);
    	final TransformComponent TRANSFORM_CURRENT = this.currentPlayer.getComponent(TransformComponent.class);
    	TRANSFORM_TARGET.x = TRANSFORM_CURRENT.x;
    	TRANSFORM_TARGET.y = TRANSFORM_CURRENT.y;
    }

    public void initializePlayer(Entity player) {
    	if(player == null) {
    		System.out.println("No se ha podido inicializar al jugador porque es nulo... | GameplayContext");
    		return;
    	}
    	
        if (player.hasComponent(PlayerComponent.class)) {
        	if(this.currentPlayer == null) {
        		this.currentPlayer = player;
        		this.addEntity(new NewEntityEvent(player));
        	}
        	
        	final PlayerType TYPE = player.getComponent(PlayerComponent.class).type;
        	
        	if(TYPE.equals(PlayerType.KRATOS)) {
        		this.kratos = player;
        	} else {
        		this.masterchief = player;
        	}
        	
        	SystemFactory.registerSystem(this.systems, new PlayerSystem(player, this.gameplayBus));
        }
    }
    
    public void initializePlayers(Entity player1, Entity player2) {
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

    public void addEntity(NewEntityEvent event) {
        this.systems.addEntity(event.entity);
    }

    public void removeEntity(RemoveEntityEvent event) {
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
        for (Disposable system : this.systems.getDISPOSABLE_SYSTEMS()) {
            system.dispose();
        }

        if (this.gameplayBus != null) {
            this.gameplayBus.clear();
            this.systems = SystemFactory.createGameplaySystems(this.batch, this.texture, this.gameplayBus);
            this.initializeEvents();
        }
        
        this.currentPlayer = null;
        this.kratos = null;
        this.masterchief = null;
    }
}
