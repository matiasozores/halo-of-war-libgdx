package com.haloofwar.systems;

import com.haloofwar.components.*;
import com.haloofwar.events.AttackEvent;
import com.haloofwar.events.EventBus;
import com.haloofwar.interfaces.Updatable;

public class PlayerWeaponInputSystem extends BaseSystem implements Updatable {

    private final EventBus bus;

    public PlayerWeaponInputSystem(EventBus bus) {
        this.bus = bus;
        this.bus.subscribe(AttackEvent.class, this::onAttack);
    }

    private void onAttack(AttackEvent event) {
        for (Entity e : this.entities) {
            if (!e.hasComponent(PlayerComponent.class)) continue;

            // --- Obtener el arma ---
            if (e.hasComponent(FireArmComponent.class)) {
                FireArmComponent weapon = e.getComponent(FireArmComponent.class);
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

            } else if (e.hasComponent(MeleeWeaponComponent.class)) {
                MeleeWeaponComponent weapon = e.getComponent(MeleeWeaponComponent.class);

                // Simplemente marcar que quiere atacar
                weapon.wantsToSwing = event.isPressed();
            }
        }
    }

    @Override
    public void register(Entity e) {
        if (e.hasComponent(PlayerComponent.class) &&
            (e.hasComponent(FireArmComponent.class) || e.hasComponent(MeleeWeaponComponent.class)) &&
            e.hasComponent(CrosshairComponent.class)) {
            super.register(e);
        }
    }

    @Override
    public void update(float delta) {
        for (Entity e : this.entities) {
            // Actualizar cooldowns
            if (e.hasComponent(FireArmComponent.class)) {
                FireArmComponent weapon = e.getComponent(FireArmComponent.class);
                if (weapon.cooldownTimer > 0f) weapon.cooldownTimer -= delta;
            } else if (e.hasComponent(MeleeWeaponComponent.class)) {
                MeleeWeaponComponent weapon = e.getComponent(MeleeWeaponComponent.class);
                if (weapon.cooldownTimer > 0f) weapon.cooldownTimer -= delta;
            }
        }
    }
}
