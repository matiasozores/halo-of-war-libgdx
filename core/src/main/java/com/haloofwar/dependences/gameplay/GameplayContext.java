package com.haloofwar.dependences.gameplay;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.dependences.assets.TextureManager;
import com.haloofwar.dependences.audio.SoundManager;
import com.haloofwar.dependences.input.InputManager;
import com.haloofwar.ecs.Entity;
import com.haloofwar.ecs.events.EventBus;
import com.haloofwar.ecs.events.types.BulletHitEvent;
import com.haloofwar.ecs.events.types.ItemPickedUpEvent;
import com.haloofwar.ecs.systems.EntitySystemInterface;
import com.haloofwar.ecs.systems.audio.SoundSystem;
import com.haloofwar.ecs.systems.collision.BulletCollisionSystem;
import com.haloofwar.ecs.systems.collision.CollisionSystem;
import com.haloofwar.ecs.systems.collision.ObstacleSystem;
import com.haloofwar.ecs.systems.collision.PickupSystem;
import com.haloofwar.ecs.systems.core.EntitySystem;
import com.haloofwar.ecs.systems.gameplay.DamageSystem;
import com.haloofwar.ecs.systems.gameplay.WeaponSystem;
import com.haloofwar.ecs.systems.physics.BulletSystem;
import com.haloofwar.ecs.systems.physics.MovementSystem;
import com.haloofwar.ecs.systems.render.AnimationSystem;
import com.haloofwar.ecs.systems.render.CrosshairSystem;
import com.haloofwar.ecs.systems.render.RenderSystem;
import com.haloofwar.ecs.systems.render.ShapeSystem;

public class GameplayContext {
	private static boolean DEBUG_MODE = false;
	
    private final EntitySystem entities;
    private Entity player;
    
    private final ArrayList<EntitySystemInterface> systems;

    
    public GameplayContext(SpriteBatch batch, InputManager input, SoundManager sound, TextureManager texture, EventBus bus) {
        this.entities = new EntitySystem();
        this.systems = this.createSystems(batch, input, sound, texture, bus);
        
        this.configureEvents(bus);
        
        if(DEBUG_MODE) {
        	this.systems.add(new ShapeSystem(batch));
        }
    }
    
    private void configureEvents(EventBus bus) {
    	bus.subscribe(ItemPickedUpEvent.class, event -> {
            this.removeEntity(event.item);
        });
    	
    	bus.subscribe(BulletHitEvent.class, event -> {
            this.removeEntity(event.bullet);
        });
    }

    private ArrayList<EntitySystemInterface> createSystems(SpriteBatch batch, InputManager input, SoundManager sound, TextureManager texture, EventBus bus) {
        ArrayList<EntitySystemInterface> systems = new ArrayList<>();

        // Sistemas de movimiento y física
        systems.add(new MovementSystem());
        BulletSystem bullets = new BulletSystem(texture, this, bus);
        systems.add(bullets);

        // Sistemas de render
        systems.add(new AnimationSystem());
        systems.add(new RenderSystem(batch));
        systems.add(new CrosshairSystem(batch));

        // Sistemas de gameplay
        systems.add(new WeaponSystem(bus));

        // Sistemas de colisión
        systems.add(new CollisionSystem(bus));
        systems.add(new ObstacleSystem(bus));
        systems.add(new PickupSystem(bus));
        systems.add(new BulletCollisionSystem(bus));
        systems.add(new DamageSystem(bus));
        
        // Sistema de audio
        systems.add(new SoundSystem(sound, bus));
        
        return systems;
    }
    
    public void addEntity(Entity e) {
        this.entities.add(e); 
        for (EntitySystemInterface system : this.systems) {
            system.register(e);
        }
    }

    public void removeEntity(Entity e) {
        this.entities.remove(e);
        for (EntitySystemInterface system : this.systems) {
            system.unregister(e);
        }
    }

    public void update(float delta) {
        for (EntitySystemInterface system : this.systems) {
            system.update(delta);
        }
    }

    public void render() {
        for (EntitySystemInterface system : this.systems) {
            system.render();
        }
    }

    public void initializePlayer(Entity player) {
        this.player = player;
        this.addEntity(player);
    }

    public Entity getPlayer() {
        return this.player;
    }
    
    public void dispose() {
        for (EntitySystemInterface system : this.systems) {
            system.dispose();
        }
        
        this.entities.clear();
    }
}
