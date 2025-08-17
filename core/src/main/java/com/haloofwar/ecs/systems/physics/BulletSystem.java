package com.haloofwar.ecs.systems.physics;

import com.haloofwar.dependences.assets.TextureManager;
import com.haloofwar.ecs.Entity;
import com.haloofwar.ecs.components.collision.CollisionComponent;
import com.haloofwar.ecs.components.gameplay.BulletComponent;
import com.haloofwar.ecs.components.physics.TransformComponent;
import com.haloofwar.ecs.components.render.RenderComponent;
import com.haloofwar.ecs.events.EventBus;
import com.haloofwar.ecs.events.types.gameplay.ShootBulletEvent;
import com.haloofwar.ecs.events.types.gameplay.SpawnBulletEvent;
import com.haloofwar.ecs.systems.BaseSystem;
import com.haloofwar.enumerators.entities.ProjectileType;
import com.haloofwar.interfaces.systems.Updatable;

public class BulletSystem extends BaseSystem implements Updatable {

    private static final int SPEED_MULTIPLIER = 10; // temporal
    private final TextureManager texture;
    private final EventBus bus;

    public BulletSystem(TextureManager texture, EventBus bus) {
        this.texture = texture;
        this.bus = bus;

        bus.subscribe(ShootBulletEvent.class, this::spawnBullet);
    }

    @Override
    public void register(Entity e) {
        if (e.hasComponent(BulletComponent.class) && e.hasComponent(RenderComponent.class)) {
            super.register(e);
        }
    }

    private void spawnBullet(ShootBulletEvent event) {
        // en un futuro fabrica de balas para separar un poco las responsabilidades y hacerlo mas escalable (tipos de balas)
        Entity bullet = new Entity();

        BulletComponent bulletComp = new BulletComponent(
            event.dirX, event.dirY, event.speed * SPEED_MULTIPLIER, event.damage
        );
        bullet.addComponent(bulletComp);

        TransformComponent transform = new TransformComponent(event.x, event.y, 16, 16);
        bullet.addComponent(transform);

        float angle = (float) Math.toDegrees(Math.atan2(event.dirY, event.dirX));
        RenderComponent render = new RenderComponent(this.texture.get(ProjectileType.BULLET), angle);
        bullet.addComponent(render);

        bullet.addComponent(new CollisionComponent(transform.width, transform.height));

        this.bus.publish(new SpawnBulletEvent(bullet));
    }

    @Override
    public void update(float delta) {
        for (Entity entity : this.entities) {
            BulletComponent bullet = entity.getComponent(BulletComponent.class);
            TransformComponent transform = entity.getComponent(TransformComponent.class);
            if (!bullet.active) continue;

            transform.x += bullet.dirX * bullet.speed * delta;
            transform.y += bullet.dirY * bullet.speed * delta;
        }
    }

    public void destroy(Entity entity) {
        if (entity.hasComponent(BulletComponent.class)) {
            entity.getComponent(BulletComponent.class).active = false;
        }
    }
}
