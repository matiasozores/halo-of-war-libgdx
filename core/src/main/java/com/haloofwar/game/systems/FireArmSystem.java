package com.haloofwar.game.systems;

import com.haloofwar.common.enums.BulletType;
import com.haloofwar.common.enums.FireArmType;
import com.haloofwar.engine.components.TransformComponent;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.PeacefulEvent;
import com.haloofwar.engine.events.ShootBulletEvent;
import com.haloofwar.engine.systems.EventSystem;
import com.haloofwar.game.components.EquipmentComponent;
import com.haloofwar.game.components.FireArmComponent;
import com.haloofwar.interfaces.Updatable;

public class FireArmSystem extends EventSystem implements Updatable {
    private final EventBus bus;
    private boolean peaceful = false;
    
    public FireArmSystem(EventBus bus) {
        this.bus = bus;
        this.listenerManager.add(bus, PeacefulEvent.class, this::onPeace);
    }

    @Override
    public void register(Entity e) {
        if (e.hasComponent(EquipmentComponent.class)) {
            EquipmentComponent equipment = e.getComponent(EquipmentComponent.class);
            if (equipment.currentWeapon != null && equipment.currentWeapon.hasComponent(FireArmComponent.class)) {
                super.register(e);
            }
        }
    }


    @Override
    public void update(float delta) {
    	if(this.peaceful) {
    		return;
    	}
    	
        for (Entity entity : this.ENTITIES) {
            EquipmentComponent equipment = entity.getComponent(EquipmentComponent.class);
            if (equipment == null || equipment.currentWeapon == null) continue;

            Entity weaponEntity = equipment.currentWeapon;
            if (!weaponEntity.hasComponent(FireArmComponent.class)) continue;

            FireArmComponent weapon = weaponEntity.getComponent(FireArmComponent.class);
            TransformComponent transform = entity.getComponent(TransformComponent.class);

            if (weapon.cooldownTimer > 0f) {
            	weapon.cooldownTimer -= delta;
            }

            if (weapon.wantsToFire && weapon.cooldownTimer <= 0f) {
                float len = (float) Math.sqrt(weapon.dirX * weapon.dirX + weapon.dirY * weapon.dirY);
                if (len == 0) continue;

                float dirX = weapon.dirX / len;
                float dirY = weapon.dirY / len;

                float bulletX = transform.x + transform.width / 2 + dirX * weapon.bulletOffset;
                float bulletY = transform.y + transform.height / 2 + dirY * weapon.bulletOffset;

                FireArmType weaponType = (FireArmType) weapon.getWeapon();
                BulletType bulletType = weaponType.getBulletType();
                
                this.bus.publish(new ShootBulletEvent(bulletX, bulletY, dirX, dirY, weapon.damage, weapon.bulletSpeed, bulletType));

                weapon.wantsToFire = false;
                weapon.cooldownTimer = weapon.fireCooldown;
            }
        }
    }
    
    private void onPeace(PeacefulEvent event) {
    	this.peaceful = event.isPeaceful;
    }

}
