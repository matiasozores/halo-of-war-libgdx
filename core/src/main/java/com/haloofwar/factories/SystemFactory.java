package com.haloofwar.factories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.dependences.SoundManager;
import com.haloofwar.dependences.TextureManager;
import com.haloofwar.events.EventBus;
import com.haloofwar.interfaces.Disposable;
import com.haloofwar.interfaces.Registrable;
import com.haloofwar.interfaces.Renderable;
import com.haloofwar.interfaces.Updatable;
import com.haloofwar.systems.AnimationSystem;
import com.haloofwar.systems.BulletCollisionSystem;
import com.haloofwar.systems.BulletSystem;
import com.haloofwar.systems.CollisionSystem;
import com.haloofwar.systems.CrosshairSystem;
import com.haloofwar.systems.DamageSystem;
import com.haloofwar.systems.DialogueSystem;
import com.haloofwar.systems.InteractionSystem;
import com.haloofwar.systems.MovementSystem;
import com.haloofwar.systems.ObstacleSystem;
import com.haloofwar.systems.PickupSystem;
import com.haloofwar.systems.RenderSystem;
import com.haloofwar.systems.SoundSystem;
import com.haloofwar.systems.TalkSystem;
import com.haloofwar.systems.WeaponSystem;
import com.haloofwar.systems.dependences.SystemCollection;

public final class SystemFactory {
	private SystemFactory() {}
	
    public static SystemCollection createGameplaySystems(
        SpriteBatch batch, SoundManager sound, 
        TextureManager texture, EventBus bus) {

        SystemCollection systems = new SystemCollection(bus);

        // array que me sirve para almacenar todo los diferentes sistemas
        Object[] allSystems = {
            new MovementSystem(bus),
            new BulletSystem(texture, bus),
            new AnimationSystem(bus),
            new RenderSystem(batch),
            new CrosshairSystem(batch),
            new WeaponSystem(bus),
            new CollisionSystem(bus),
            new ObstacleSystem(bus),
            new PickupSystem(bus),
            new TalkSystem(bus),
            new BulletCollisionSystem(bus),
            new DamageSystem(bus),
            new SoundSystem(sound, bus),
            new InteractionSystem(bus),
            new DialogueSystem(bus)
        };

        for (Object system : allSystems) {
            registerSystem(systems, system);
        }

        return systems;
    }

    // No es muy escalable pero por ahora lo dejamos asi
    public static void registerSystem(SystemCollection collection, Object system) {
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
