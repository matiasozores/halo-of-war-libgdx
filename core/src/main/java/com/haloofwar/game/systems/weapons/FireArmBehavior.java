package com.haloofwar.game.systems.weapons;

import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.online.ShootBulletEventOnline;
import com.haloofwar.game.components.FireArmComponent;
import com.haloofwar.game.components.TransformComponent;

public class FireArmBehavior implements WeaponBehavior {

    @Override
    public void update(Entity owner, Entity weapon, float delta, EventBus bus) {
        FireArmComponent firearm = weapon.getComponent(FireArmComponent.class);
        TransformComponent transform = owner.getComponent(TransformComponent.class);

        if (firearm.cooldownTimer > 0f) {
            firearm.cooldownTimer -= delta;
            return;
        }

        if (!firearm.wantsToFire) {
        	return;
        }

        float len = (float) Math.sqrt(firearm.dirX * firearm.dirX + firearm.dirY * firearm.dirY);
        
        if (len == 0) {
        	return;
        }

        float dirX = firearm.dirX / len;
        float dirY = firearm.dirY / len;

        float bulletX = transform.x + transform.width / 2 + dirX * firearm.bulletOffset;
        float bulletY = transform.y + transform.height / 2 + dirY * firearm.bulletOffset;

        bus.publish(new ShootBulletEventOnline(bulletX, bulletY, dirX, dirY,
                firearm.damage, firearm.bulletSpeed, firearm.getWeapon().getBulletType()));

        firearm.cooldownTimer = firearm.fireCooldown;
        firearm.wantsToFire = false;
    }
}
