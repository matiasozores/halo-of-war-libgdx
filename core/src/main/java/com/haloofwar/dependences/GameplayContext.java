package com.haloofwar.dependences;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.components.Entity;
import com.haloofwar.components.PlayerComponent;
import com.haloofwar.events.EventBus;
import com.haloofwar.events.NewEntityEvent;
import com.haloofwar.events.RemoveEntityEvent;
import com.haloofwar.factories.SystemFactory;
import com.haloofwar.interfaces.Disposable;
import com.haloofwar.interfaces.Renderable;
import com.haloofwar.interfaces.Updatable;
import com.haloofwar.systems.PlayerSystem;
import com.haloofwar.systems.dependences.SystemCollection;

public class GameplayContext {
    private final SystemCollection SYSTEMS;
    private final EventBus gameplayBus;
    private Entity player; 
    
    public GameplayContext(SpriteBatch batch, TextureManager texture, InputManager input) {
    	this.gameplayBus = new EventBus();
    	input.setGameplayBus(this.gameplayBus);
    	this.SYSTEMS = SystemFactory.createGameplaySystems(batch, texture, this.gameplayBus);   
    	
    	this.gameplayBus.subscribe(NewEntityEvent.class, this::addEntity);
    	this.gameplayBus.subscribe(RemoveEntityEvent.class, this::removeEntity);
    }

    public void update(float delta) {
        for (Updatable system : this.SYSTEMS.getUPDATE_SYSTEMS()) {
            system.update(delta);
        }
    }

    public void render() {
        for (Renderable system : this.SYSTEMS.getRENDER_SYSTEMS()) {
            system.render();
        }
    }
    
    // Agregar entidades (encapsulado)
    
    public void addEntity(NewEntityEvent event) {
    	this.SYSTEMS.addEntity(event.entity);
    }
    
    public void removeEntity(Entity entity) {
    	this.SYSTEMS.removeEntity(entity);
    }
    
    public void removeEntity(RemoveEntityEvent event) {
    	this.SYSTEMS.removeEntity(event.entity);
    }
    
    // Agregar jugador
    
    public void initializePlayer(Entity player) {
    	if(player.hasComponent(PlayerComponent.class)) {
    		this.player = player;
    		this.addEntity(new NewEntityEvent(this.player));
    		SystemFactory.registerSystem(this.SYSTEMS, new PlayerSystem(player, this.gameplayBus));
    	}
    }
    
    public Entity getPlayer() {
		return this.player;
	}
    
    public void dispose() {
        for (Disposable system : this.SYSTEMS.getDISPOSABLE_SYSTEMS()) {
            system.dispose();
        }
        
        this.player = null;
        this.gameplayBus.clear();
    }
    
    public EventBus getBus() {
		return this.gameplayBus;
	}

}
