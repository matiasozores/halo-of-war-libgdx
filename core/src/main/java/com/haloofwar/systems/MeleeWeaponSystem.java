package com.haloofwar.systems;

import com.haloofwar.components.Entity;
import com.haloofwar.components.MeleeWeaponComponent;
import com.haloofwar.components.TransformComponent;
import com.haloofwar.events.EventBus;
import com.haloofwar.interfaces.Updatable;

public class MeleeWeaponSystem extends BaseSystem implements Updatable {
    private final EventBus bus;

    public MeleeWeaponSystem(EventBus bus) {
        this.bus = bus;
    }

    @Override
    public void register(Entity e) {
        if (e.hasComponent(MeleeWeaponComponent.class) && e.hasComponent(TransformComponent.class)) {
            super.register(e);
        }
    }

    @Override
    public void update(float delta) {
        for (Entity entity : this.entities) {
            MeleeWeaponComponent weapon = entity.getComponent(MeleeWeaponComponent.class);
            TransformComponent transform = entity.getComponent(TransformComponent.class);

            if (weapon.cooldownTimer > 0f) {
                weapon.cooldownTimer -= delta;
            }

            if (weapon.wantsToSwing && weapon.cooldownTimer <= 0f) {
                // ✅ Calcular centro del atacante
                float centerX = transform.x + transform.width / 2;	
                float centerY = transform.y + transform.height / 2;

                // ✅ Calcular coordenadas máximas de alcance
                float minX = centerX - weapon.range;
                float maxX = centerX + weapon.range;
                float minY = centerY - weapon.range;
                float maxY = centerY + weapon.range;
                
                // Aquí podrías iterar sobre todos los enemigos/jugadores y aplicar daño si están dentro de estas coordenadas
                // por ejemplo:
                // for(Entity target : allEntities) { ... calcular distancia ... if(dist <= weapon.range) { aplicar daño } }

                weapon.wantsToSwing = false;
                weapon.cooldownTimer = weapon.fireCooldown;
            }
        }
    }

}
