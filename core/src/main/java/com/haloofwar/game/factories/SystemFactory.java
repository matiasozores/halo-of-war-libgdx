package com.haloofwar.game.factories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.systems.MovementSystem;
import com.haloofwar.engine.systems.RenderSystem;
import com.haloofwar.engine.systems.SystemCollection;
import com.haloofwar.engine.systems.VisibilitySystem;
import com.haloofwar.game.systems.AnimationSystem;
import com.haloofwar.game.systems.BulletCollisionSystem;
import com.haloofwar.game.systems.BulletSystem;
import com.haloofwar.game.systems.CollectSystem;
import com.haloofwar.game.systems.CollisionSystem;
import com.haloofwar.game.systems.CrosshairSystem;
import com.haloofwar.game.systems.DamageSystem;
import com.haloofwar.game.systems.DialogueSystem;
import com.haloofwar.game.systems.DropSystem;
import com.haloofwar.game.systems.EnemySystem;
import com.haloofwar.game.systems.EnemyWeaponAISystem;
import com.haloofwar.game.systems.FireArmSystem;
import com.haloofwar.game.systems.MeleeWeaponSystem;
import com.haloofwar.game.systems.ObstacleSystem;
import com.haloofwar.game.systems.PickupSystem;
import com.haloofwar.game.systems.PlayerWeaponInputSystem;
import com.haloofwar.game.systems.PortalSystem;
import com.haloofwar.game.systems.PowerUpSystem;
import com.haloofwar.game.systems.TalkSystem;

public final class SystemFactory {
	private SystemFactory() {}
	
    public static SystemCollection createGameplaySystems(
        SpriteBatch batch, TextureManager texture, EventBus bus) {

        SystemCollection systems = new SystemCollection();

        // array que me sirve para almacenar todo los diferentes sistemas
        Object[] allSystems = {
            new MovementSystem(bus),
            new BulletSystem(texture, bus),
            new AnimationSystem(bus),
            new RenderSystem(batch),
            new CrosshairSystem(bus, batch),
            new MeleeWeaponSystem(bus),
            new FireArmSystem(bus),
            new CollisionSystem(bus),
            new ObstacleSystem(bus),
            new PickupSystem(bus),
            new TalkSystem(bus),
            new BulletCollisionSystem(bus),
            new DamageSystem(bus),
            new DialogueSystem(bus),
            new PortalSystem(bus),
            new PlayerWeaponInputSystem(bus),
            new CollectSystem(bus),
            new PowerUpSystem(bus),
            new EnemyWeaponAISystem(bus),
            new VisibilitySystem(),
            new EnemySystem(bus),
            new DropSystem(bus, texture)
        };

        for (Object system : allSystems) {
            registerSystem(systems, system);
        }

        return systems;
    }


    public static void registerSystem(SystemCollection collection, Object system) {
    	collection.addSystem(system);
    }
}
