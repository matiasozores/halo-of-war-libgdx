package com.haloofwar.systems;

import com.haloofwar.components.CrosshairComponent;
import com.haloofwar.components.Entity;
import com.haloofwar.components.PlayerComponent;
import com.haloofwar.components.TransformComponent;
import com.haloofwar.components.WeaponComponent;
import com.haloofwar.events.AttackEvent;
import com.haloofwar.events.EventBus;
import com.haloofwar.events.ShootBulletEvent;
import com.haloofwar.interfaces.Updatable;

public class WeaponSystem extends BaseSystem implements Updatable {
    private EventBus bus;
    
    public WeaponSystem(EventBus bus) {
        this.bus = bus;
        this.bus.subscribe(AttackEvent.class, this::onAttack);
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

            if (weapon.isReady && weapon.wantsToFire) {
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

        float offset = 45f; // cambiar a futuro para no dejar en numeros magicos
        float bulletX = transform.x + dirX * offset;
        float bulletY = transform.y + dirY * offset;

        
        ShootBulletEvent bullet = new ShootBulletEvent(bulletX, bulletY, dirX, dirY, weapon.damage, weapon.speed);
        this.bus.publish(bullet);
     

        weapon.isReady = false;
        weapon.currentCooldown = weapon.cooldown;
    
    }
    
    /*
     * En realidad hay que crear un sistema de armas dividido para que 
     * uno pueda tener en cuenta el input del jugador y el otro que sea para los enemigos
     * 
     * */

    private void onAttack(AttackEvent event) {
        for (Entity e : this.entities) {
            if (e.hasComponent(PlayerComponent.class)) { 
                WeaponComponent weapon = e.getComponent(WeaponComponent.class);
                weapon.wantsToFire = event.isPressed();
            }
        }
    }

}
