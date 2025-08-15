package com.haloofwar.ecs.systems.gameplay;

import com.haloofwar.dependences.input.InputManager;
import com.haloofwar.ecs.Entity;
import com.haloofwar.ecs.components.gameplay.WeaponComponent;
import com.haloofwar.ecs.components.physics.TransformComponent;
import com.haloofwar.ecs.components.render.CrosshairComponent;
import com.haloofwar.ecs.events.EventBus;
import com.haloofwar.ecs.events.types.PlaySoundEvent;
import com.haloofwar.ecs.systems.BaseSystem;
import com.haloofwar.ecs.systems.physics.BulletSystem;
import com.haloofwar.enumerators.game.SoundType;

public class WeaponSystem extends BaseSystem {

    private final InputManager input;
    private final BulletSystem bulletSystem;
    private final EventBus bus;
    
    public WeaponSystem(InputManager input, BulletSystem bulletSystem, EventBus bus) {
        this.input = input;
        this.bulletSystem = bulletSystem;
        this.bus = bus;
    }

    @Override
    public void register(Entity e) {
        if (e.hasComponent(WeaponComponent.class) &&
            e.hasComponent(TransformComponent.class) &&
            e.hasComponent(CrosshairComponent.class)) {
            super.register(e);
        }
    }
    
    @Override
    public void update(float delta) {
        for (Entity entity : this.entities) {
            WeaponComponent weapon = entity.getComponent(WeaponComponent.class);
            TransformComponent transform = entity.getComponent(TransformComponent.class);
            CrosshairComponent crosshair = entity.getComponent(CrosshairComponent.class);

            this.updateCooldown(weapon, delta);

            if (weapon.isReady && this.input.isAttack()) {
                this.fireBullet(entity, weapon, transform, crosshair);
            }
        }
    }
    
    private void updateCooldown(WeaponComponent weapon, float delta) {
        if (!weapon.isReady) {
            weapon.currentCooldown -= delta;
            if (weapon.currentCooldown <= 0) {
                weapon.isReady = true;
                weapon.currentCooldown = 0;
            }
        }
    }
    
    private void fireBullet(Entity entity, WeaponComponent weapon, TransformComponent transform, CrosshairComponent crosshair) {
        float dx = crosshair.mouseX - transform.x;
        float dy = crosshair.mouseY - transform.y;
        float length = (float) Math.sqrt(dx * dx + dy * dy);
        if (length == 0) return;

        float dirX = dx / length;
        float dirY = dy / length;

        float offset = 45f; // cambiar a futuro para algo mas sensato
        float bulletX = transform.x + dirX * offset;
        float bulletY = transform.y + dirY * offset;

        this.bulletSystem.spawnBullet(
            bulletX, bulletY, dirX, dirY, weapon.damage, weapon.speed
        );

        weapon.isReady = false;
        weapon.currentCooldown = weapon.cooldown;
    
        this.bus.publish(new PlaySoundEvent(SoundType.SHOOT_ASSAULT_RIFLE));
    }

}
