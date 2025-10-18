package com.haloofwar.game.systems;

import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.NewEntityEvent;
import com.haloofwar.engine.events.PlaySoundEvent;
import com.haloofwar.engine.events.ShootBulletEvent;
import com.haloofwar.engine.events.online.ShootBulletEventOnline;
import com.haloofwar.engine.interfaces.MovementController;
import com.haloofwar.engine.utils.RandomUtils;
import com.haloofwar.game.components.BulletComponent;
import com.haloofwar.game.components.BulletMovementController;
import com.haloofwar.game.components.CollisionComponent;
import com.haloofwar.game.components.MovementComponent;
import com.haloofwar.game.components.RenderComponent;
import com.haloofwar.game.components.TransformComponent;

public class BulletSystem extends EventSystem {

    private static final int SPEED_MULTIPLIER = 10; // temporal
    private final TextureManager texture;

    public BulletSystem(TextureManager texture, EventBus bus) {
        super(bus);
    	this.texture = texture;
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

        TransformComponent transform = new TransformComponent(RandomUtils.generateUniqueId(), event.x, event.y, 16, 16);
        bullet.addComponent(transform);

        float angle = (float) Math.toDegrees(Math.atan2(event.dirY, event.dirX)) - 90f;

        RenderComponent render = new RenderComponent(this.texture.get(event.type), angle);
        bullet.addComponent(render);
        
        MovementController controller = new BulletMovementController(transform, event.dirX, event.dirY, event.speed * SPEED_MULTIPLIER);
        MovementComponent mc = new MovementComponent(controller, event.speed);
        bullet.addComponent(mc);
        
        bullet.addComponent(new CollisionComponent(transform.width, transform.height));

        
        this.bus.publish(new NewEntityEvent(bullet));
        this.bus.publish(new PlaySoundEvent(event.type.getSound()));
        this.bus.publish(new ShootBulletEventOnline(transform.identifier, event.x, event.y, event.dirX, event.dirY, event.damage, event.speed, event.type));
    }

    public void destroy(Entity entity) {
        if (entity.hasComponent(BulletComponent.class)) {
            entity.getComponent(BulletComponent.class).active = false;
        }
    }
}
