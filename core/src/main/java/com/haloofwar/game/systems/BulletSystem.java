package com.haloofwar.game.systems;

import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.NewEntityEvent;
import com.haloofwar.engine.events.PlaySoundEvent;
import com.haloofwar.engine.events.ShootBulletEvent;
import com.haloofwar.engine.interfaces.MovementController;
import com.haloofwar.game.components.BulletComponent;
import com.haloofwar.game.components.BulletMovementController;
import com.haloofwar.game.components.MovementComponent;
import com.haloofwar.game.components.RenderComponent;
import com.haloofwar.game.components.TransformComponent;

public class BulletSystem extends EventSystem {

    private static final int SPEED_MULTIPLIER = 10; // temporal
    private final TextureManager texture;
    private final EventBus bus;

    public BulletSystem(TextureManager texture, EventBus bus) {
        this.texture = texture;
        this.bus = bus;

        this.listenerManager.add(bus, ShootBulletEvent.class, this::spawnBullet);
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
        TransformComponent transform = new TransformComponent(event.identifier, event.x, event.y, 16, 16);
        bullet.addComponent(transform);

        // --- Render con rotación ---
        float angle = (float) Math.toDegrees(Math.atan2(event.dirY, event.dirX)) - 90f;

        RenderComponent render = new RenderComponent(this.texture.get(event.type), angle);
        bullet.addComponent(render);

        MovementController controller = new BulletMovementController(transform, event.dirX, event.dirY, event.speed * SPEED_MULTIPLIER);
        MovementComponent mc = new MovementComponent(controller, event.speed);
        bullet.addComponent(mc);
        
        this.bus.publish(new NewEntityEvent(bullet));
        this.bus.publish(new PlaySoundEvent(event.type.getSound()));
    }

    public void destroy(Entity entity) {
        if (entity.hasComponent(BulletComponent.class)) {
            entity.getComponent(BulletComponent.class).active = false;
        }
    }
}
