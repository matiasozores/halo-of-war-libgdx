package com.haloofwar.game.systems;

import com.haloofwar.engine.components.TransformComponent;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.AttackEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.systems.BaseSystem;
import com.haloofwar.game.components.CrosshairComponent;
import com.haloofwar.game.components.EquipmentComponent;
import com.haloofwar.game.components.FireArmComponent;
import com.haloofwar.game.components.MeleeWeaponComponent;
import com.haloofwar.game.components.PlayerComponent;
import com.haloofwar.interfaces.Updatable;

public class PlayerWeaponInputSystem extends BaseSystem implements Updatable {

    private final EventBus bus;

    public PlayerWeaponInputSystem(EventBus bus) {
        this.bus = bus;
        this.bus.subscribe(AttackEvent.class, this::onAttack);
    }

    private void onAttack(AttackEvent event) {
        for (Entity e : this.ENTITIES) {
            if (!e.hasComponent(PlayerComponent.class)) continue;

            EquipmentComponent equipment = e.getComponent(EquipmentComponent.class);
            if (equipment == null || equipment.currentWeapon == null) continue;

            Entity weaponEntity = equipment.currentWeapon;

            // --- Arma de fuego ---
            if (weaponEntity.hasComponent(FireArmComponent.class)) {
                FireArmComponent weapon = weaponEntity.getComponent(FireArmComponent.class);
                CrosshairComponent crosshair = e.getComponent(CrosshairComponent.class);
                TransformComponent transform = e.getComponent(TransformComponent.class);

                // Calcular dirección hacia el mouse
                float dx = crosshair.mouseX - (transform.x + transform.width / 2);
                float dy = crosshair.mouseY - (transform.y + transform.height / 2);
                float length = (float) Math.sqrt(dx*dx + dy*dy);
                if (length > 0) {
                    weapon.dirX = dx / length;
                    weapon.dirY = dy / length;
                }

                weapon.wantsToFire = event.isPressed();

            // --- Arma cuerpo a cuerpo ---
            } else if (weaponEntity.hasComponent(MeleeWeaponComponent.class)) {
                MeleeWeaponComponent weapon = weaponEntity.getComponent(MeleeWeaponComponent.class);
                weapon.wantsToSwing = event.isPressed();
            }
        }
    }

    @Override
    public void register(Entity e) {
        if (e.hasComponent(PlayerComponent.class) && e.hasComponent(EquipmentComponent.class) &&
            e.hasComponent(CrosshairComponent.class)) {
            super.register(e);
        }
    }

    @Override
    public void update(float delta) {
        for (Entity e : this.ENTITIES) {
            EquipmentComponent equipment = e.getComponent(EquipmentComponent.class);
            if (equipment == null || equipment.currentWeapon == null) continue;

            Entity weaponEntity = equipment.currentWeapon;

            if (weaponEntity.hasComponent(FireArmComponent.class)) {
                FireArmComponent weapon = weaponEntity.getComponent(FireArmComponent.class);
                if (weapon.cooldownTimer > 0f) weapon.cooldownTimer -= delta;
            } else if (weaponEntity.hasComponent(MeleeWeaponComponent.class)) {
                MeleeWeaponComponent weapon = weaponEntity.getComponent(MeleeWeaponComponent.class);
                if (weapon.cooldownTimer > 0f) weapon.cooldownTimer -= delta;
            }
        }
    }
}
