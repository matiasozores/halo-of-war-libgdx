package com.haloofwar.systems;

import com.haloofwar.components.Entity;
import com.haloofwar.components.TransformComponent;
import com.haloofwar.components.FireArmComponent;
import com.haloofwar.events.ShootBulletEvent;
import com.haloofwar.events.EventBus;
import com.haloofwar.interfaces.Updatable;

public class FireArmSystem extends BaseSystem implements Updatable {
    private final EventBus bus;

    public FireArmSystem(EventBus bus) {
        this.bus = bus;
    }

    @Override
    public void register(Entity e) {
        if (e.hasComponent(FireArmComponent.class) && e.hasComponent(TransformComponent.class)) {
            super.register(e);
        }
    }

    @Override
    public void update(float delta) {
        for (Entity entity : this.entities) {
            FireArmComponent weapon = entity.getComponent(FireArmComponent.class);
            TransformComponent transform = entity.getComponent(TransformComponent.class);

            if (weapon.cooldownTimer > 0f) weapon.cooldownTimer -= delta;

            if (weapon.wantsToFire && weapon.cooldownTimer <= 0f) {
                float len = (float) Math.sqrt(weapon.dirX * weapon.dirX + weapon.dirY * weapon.dirY);
                if (len == 0) continue;

                float dirX = weapon.dirX / len;
                float dirY = weapon.dirY / len;

                float bulletX = transform.x + transform.width / 2 + dirX * weapon.bulletOffset;
                float bulletY = transform.y + transform.height / 2 + dirY * weapon.bulletOffset;

                this.bus.publish(new ShootBulletEvent(bulletX, bulletY, dirX, dirY, weapon.damage, weapon.bulletSpeed));

                weapon.wantsToFire = false;
                weapon.cooldownTimer = weapon.fireCooldown;
            }
        }
    }
}
