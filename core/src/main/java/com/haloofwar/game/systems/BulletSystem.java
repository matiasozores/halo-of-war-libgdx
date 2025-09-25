package com.haloofwar.game.systems;

import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.components.RenderComponent;
import com.haloofwar.engine.components.TransformComponent;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.NewEntityEvent;
import com.haloofwar.engine.events.PlaySoundEvent;
import com.haloofwar.engine.events.ShootBulletEvent;
import com.haloofwar.engine.systems.BaseSystem;
import com.haloofwar.game.components.BulletComponent;
import com.haloofwar.game.components.CollisionComponent;
import com.haloofwar.interfaces.Updatable;

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
        if (e.hasComponent(BulletComponent.class) &&
            e.hasComponent(RenderComponent.class)) {
            super.register(e);
        }
    }

    private void spawnBullet(ShootBulletEvent event) {
        Entity bullet = new Entity();

        // --- Componente de bala ---
        BulletComponent bulletComp = new BulletComponent(
            event.dirX,
            event.dirY,
            event.speed * SPEED_MULTIPLIER,
            event.damage
        );
        bullet.addComponent(bulletComp);

        // --- Transform inicial ---
        TransformComponent transform = new TransformComponent(event.x, event.y, 16, 16);
        bullet.addComponent(transform);

        // --- Render con rotación ---
        float angle = (float) Math.toDegrees(Math.atan2(event.dirY, event.dirX)) - 90f;

        RenderComponent render = new RenderComponent(this.texture.get(event.type), angle);
        bullet.addComponent(render);

        // --- Colisión ---
        bullet.addComponent(new CollisionComponent(transform.width, transform.height));

        this.bus.publish(new NewEntityEvent(bullet));
        this.bus.publish(new PlaySoundEvent(event.type.getSound()));
    }

    @Override
    public void update(float delta) {
        for (Entity entity : this.ENTITIES) {
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
