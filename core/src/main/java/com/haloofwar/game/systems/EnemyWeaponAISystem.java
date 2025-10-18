package com.haloofwar.game.systems;

import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.ChangeTargetEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.ShootBulletEvent;
import com.haloofwar.game.components.EnemyComponent;
import com.haloofwar.game.components.EnemyWeaponAIComponent;
import com.haloofwar.game.components.EquipmentComponent;
import com.haloofwar.game.components.FireArmComponent;
import com.haloofwar.game.components.TargetComponent;
import com.haloofwar.game.components.TransformComponent;
import com.haloofwar.interfaces.Updatable;

public class EnemyWeaponAISystem extends EventSystem implements Updatable {

	
    public EnemyWeaponAISystem(final EventBus gameplayBus) {
    	super(gameplayBus);
    	this.listenerManager.add(gameplayBus, ChangeTargetEvent.class, this::onChangeTarget);
    }
    
    private void onChangeTarget(ChangeTargetEvent event) {
    	for (Entity entity : this.ENTITIES) {
			if(entity.hasComponent(EnemyComponent.class)) {
				TargetComponent target = entity.getComponent(TargetComponent.class);
				target.targetTransform = event.newTarget.getComponent(TransformComponent.class);
			}
		}
    }
    
    @Override
    public void register(Entity e) {
        if (e.hasComponent(EquipmentComponent.class) &&
            e.hasComponent(TargetComponent.class) &&
            e.hasComponent(EnemyWeaponAIComponent.class)) {
            super.register(e);
        }
    }

    @Override
    public void update(float delta) {
        for (Entity enemy : this.ENTITIES) {
            EnemyWeaponAIComponent weaponAI = enemy.getComponent(EnemyWeaponAIComponent.class);
            TargetComponent target = enemy.getComponent(TargetComponent.class);
            TransformComponent transform = enemy.getComponent(TransformComponent.class);
            EquipmentComponent equipment = enemy.getComponent(EquipmentComponent.class);

            if (equipment == null || equipment.currentWeapon == null) continue;

            Entity weaponEntity = equipment.currentWeapon;

            weaponAI.shootTimer += delta;

            // --- ARMA DE FUEGO ---
            if (weaponEntity.hasComponent(FireArmComponent.class)) {
                FireArmComponent weapon = weaponEntity.getComponent(FireArmComponent.class);

                float dx = target.targetTransform.x - (transform.x + transform.width / 2);
                float dy = target.targetTransform.y - (transform.y + transform.height / 2);
                float length = (float) Math.sqrt(dx*dx + dy*dy);
                if (length > 0) {
                    weapon.dirX = dx / length;
                    weapon.dirY = dy / length;
                }

                if (weapon.cooldownTimer <= 0f && weaponAI.shootTimer >= weaponAI.shootInterval) {
                	
                    float bulletX = transform.x + transform.width / 2 + weapon.dirX * weapon.bulletOffset;
                    float bulletY = transform.y + transform.height / 2 + weapon.dirY * weapon.bulletOffset;
                	
                    this.bus.publish(new ShootBulletEvent(bulletX, bulletY, weapon.dirX, weapon.dirY, weapon.damage, weapon.bulletSpeed, weapon.type.getBulletType()));
                	weapon.wantsToFire = true;
                	 weaponAI.shootTimer = 0f;
                    weapon.cooldownTimer = weapon.fireCooldown;
                } else {
                	weapon.cooldownTimer -= delta;
                }
            }	
        }
    }
}
