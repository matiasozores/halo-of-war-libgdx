package com.haloofwar.dependences.gameplay;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.dependences.assets.TextureManager;
import com.haloofwar.dependences.audio.SoundManager;
import com.haloofwar.dependences.input.InputManager;
import com.haloofwar.ecs.Entity;
import com.haloofwar.ecs.components.collision.PlayerComponent;
import com.haloofwar.ecs.events.EventBus;
import com.haloofwar.ecs.systems.dependences.SystemCollection;
import com.haloofwar.factories.SystemFactory;
import com.haloofwar.interfaces.systems.Disposable;
import com.haloofwar.interfaces.systems.Renderable;
import com.haloofwar.interfaces.systems.Updatable;

public class GameplayContext {
    private final SystemCollection SYSTEMS;
    
    // hay acoplamiento por parte de esta entidad que actua como player
    private Entity player;
    
    public GameplayContext(SpriteBatch batch, InputManager input, SoundManager sound, TextureManager texture, EventBus bus) {
    	this.SYSTEMS = SystemFactory.createGameplaySystems(batch, input, sound, texture, bus);   
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
    
    public void addEntity(Entity entity) {
    	this.SYSTEMS.addEntity(entity);
    }
    
    public void removeEntity(Entity entity) {
    	this.SYSTEMS.removeEntity(entity);
    }

    public void initializePlayer(Entity player) {
    	if(player.hasComponent(PlayerComponent.class)) {
            this.player = player;
            this.addEntity(player);
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
    }
}
