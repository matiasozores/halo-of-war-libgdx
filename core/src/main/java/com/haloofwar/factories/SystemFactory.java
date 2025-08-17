package com.haloofwar.factories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.dependences.assets.TextureManager;
import com.haloofwar.dependences.audio.SoundManager;
import com.haloofwar.dependences.input.InputManager;
import com.haloofwar.ecs.events.EventBus;
import com.haloofwar.ecs.systems.audio.SoundSystem;
import com.haloofwar.ecs.systems.collision.BulletCollisionSystem;
import com.haloofwar.ecs.systems.collision.CollisionSystem;
import com.haloofwar.ecs.systems.collision.ObstacleSystem;
import com.haloofwar.ecs.systems.collision.PickupSystem;
import com.haloofwar.ecs.systems.dependences.SystemCollection;
import com.haloofwar.ecs.systems.gameplay.DamageSystem;
import com.haloofwar.ecs.systems.gameplay.WeaponSystem;
import com.haloofwar.ecs.systems.physics.BulletSystem;
import com.haloofwar.ecs.systems.physics.MovementSystem;
import com.haloofwar.ecs.systems.render.AnimationSystem;
import com.haloofwar.ecs.systems.render.CrosshairSystem;
import com.haloofwar.ecs.systems.render.RenderSystem;
import com.haloofwar.interfaces.systems.Disposable;
import com.haloofwar.interfaces.systems.Registrable;
import com.haloofwar.interfaces.systems.Renderable;
import com.haloofwar.interfaces.systems.Updatable;

public class SystemFactory {

    public static SystemCollection createGameplaySystems(
        SpriteBatch batch, InputManager input, 
        SoundManager sound, TextureManager texture,
        EventBus bus) {

        SystemCollection systems = new SystemCollection(bus);

        // array que me sirve para almacenar todo los diferentes sistemas
        Object[] allSystems = {
            new MovementSystem(),
            new BulletSystem(texture, bus),
            new AnimationSystem(),
            new RenderSystem(batch),
            new CrosshairSystem(batch),
            new WeaponSystem(bus),
            new CollisionSystem(bus),
            new ObstacleSystem(bus),
            new PickupSystem(bus),
            new BulletCollisionSystem(bus),
            new DamageSystem(bus),
            new SoundSystem(sound, bus)
        };

        for (Object system : allSystems) {
            registerSystem(systems, system);
        }

        return systems;
    }

    // No es muy escalable pero por ahora lo dejamos asi
    private static void registerSystem(SystemCollection collection, Object system) {
        if (system instanceof Updatable updatable) {
        	collection.getUPDATE_SYSTEMS().add(updatable);
        }
        
        if (system instanceof Renderable renderable) {
        	collection.getRENDER_SYSTEMS().add(renderable);
        }
        
        if (system instanceof Registrable registrable) {
        	collection.getREGISTRY_SYSTEMS().add(registrable);
        }
        
        if (system instanceof Disposable disposable) {
        	collection.getDISPOSABLE_SYSTEMS().add(disposable);
        }
    }
}
