package com.haloofwar.game.systems;

import com.haloofwar.engine.components.TransformComponent;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.NewPlayerEvent;
import com.haloofwar.engine.interfaces.MovementController;
import com.haloofwar.engine.systems.EventSystem;
import com.haloofwar.game.components.EnemyWeaponAIComponent;
import com.haloofwar.game.components.EquipmentComponent;
import com.haloofwar.game.components.FireArmComponent;
import com.haloofwar.game.components.MeleeWeaponComponent;
import com.haloofwar.game.components.MovementComponent;
import com.haloofwar.game.components.TargetComponent;
import com.haloofwar.interfaces.Updatable;

public class EnemyWeaponAISystem extends EventSystem implements Updatable {

    private float shootInterval = 0.2f;

    public EnemyWeaponAISystem(final EventBus gameplayBus) {
    	this.listenerManager.add(gameplayBus, NewPlayerEvent.class, this::onNewPlayer);
    }
    
    private void onNewPlayer(NewPlayerEvent event) {
    	for (Entity entity : this.ENTITIES) {
    		
			final MovementController CONTROLLER = entity.getComponent(MovementComponent.class).controller;
			final TargetComponent TARGET = entity.getComponent(TargetComponent.class);
			TARGET.targetTransform = event.player.getComponent(TransformComponent.class);
			CONTROLLER.changeTarget(event.player);
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

                if (weapon.cooldownTimer <= 0f && weaponAI.shootTimer >= shootInterval) {
                    weapon.wantsToFire = true;
                    weaponAI.shootTimer = 0f;
                    weapon.cooldownTimer = weapon.fireCooldown;
                }

            // --- ARMA CUERPO A CUERPO ---
            } else if (weaponEntity.hasComponent(MeleeWeaponComponent.class)) {
                MeleeWeaponComponent weapon = weaponEntity.getComponent(MeleeWeaponComponent.class);

                float dx = target.targetTransform.x - (transform.x + transform.width / 2);
                float dy = target.targetTransform.y - (transform.y + transform.height / 2);
                float distance = (float) Math.sqrt(dx*dx + dy*dy);

                if (distance <= weapon.range && weapon.cooldownTimer <= 0f && weaponAI.shootTimer >= shootInterval) {
                    weapon.wantsToSwing = true;
                    weaponAI.shootTimer = 0f;
                    weapon.cooldownTimer = weapon.fireCooldown;
                }
            }
        }
    }
}
