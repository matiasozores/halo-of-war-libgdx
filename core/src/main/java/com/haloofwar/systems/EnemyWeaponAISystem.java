package com.haloofwar.systems;

import com.haloofwar.components.*;
import com.haloofwar.interfaces.Updatable;

public class EnemyWeaponAISystem extends BaseSystem implements Updatable {

    private float shootInterval = 0.2f; // tiempo mínimo entre ataques

    @Override
    public void register(Entity e) {
        if ((e.hasComponent(FireArmComponent.class) || e.hasComponent(MeleeWeaponComponent.class)) &&
            e.hasComponent(TargetComponent.class) &&
            e.hasComponent(EnemyWeaponAIComponent.class)) {
            super.register(e);
        }
    }

    @Override
    public void update(float delta) {
        for (Entity enemy : this.entities) {
            EnemyWeaponAIComponent weaponAI = enemy.getComponent(EnemyWeaponAIComponent.class);
            TargetComponent target = enemy.getComponent(TargetComponent.class);
            TransformComponent transform = enemy.getComponent(TransformComponent.class);

            // Actualizar timer del enemigo
            weaponAI.shootTimer += delta;

            // --- ARMA DE FUEGO ---
            if (enemy.hasComponent(FireArmComponent.class)) {
                FireArmComponent weapon = enemy.getComponent(FireArmComponent.class);

                // Calcular dirección hacia el target
                float dx = target.targetTransform.x - (transform.x + transform.width / 2);
                float dy = target.targetTransform.y - (transform.y + transform.height / 2);
                float length = (float) Math.sqrt(dx*dx + dy*dy);
                if (length > 0) {
                    weapon.dirX = dx / length;
                    weapon.dirY = dy / length;
                }

                // Disparar si cooldown y timer lo permiten
                if (weapon.cooldownTimer <= 0f && weaponAI.shootTimer >= shootInterval) {
                    weapon.wantsToFire = true;
                    weaponAI.shootTimer = 0f;
                    weapon.cooldownTimer = weapon.fireCooldown;
                }

            // --- ARMA CUERPO A CUERPO ---
            } else if (enemy.hasComponent(MeleeWeaponComponent.class)) {
                MeleeWeaponComponent weapon = enemy.getComponent(MeleeWeaponComponent.class);

                // Atacar si el target está dentro del rango
                float dx = target.targetTransform.x - (transform.x + transform.width / 2);
                float dy = target.targetTransform.y - (transform.y + transform.height / 2);
                float distance = (float) Math.sqrt(dx*dx + dy*dy);

                if (distance <= weapon.range && weapon.cooldownTimer <= 0f && weaponAI.shootTimer >= shootInterval) {
                    weapon.wantsToSwing = true;
                    weaponAI.shootTimer = 0f;
                    weapon.cooldownTimer = 1f;
                }
            }
        }
    }
}
