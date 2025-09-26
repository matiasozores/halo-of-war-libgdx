package com.haloofwar.game.systems;

import java.util.ArrayList;
import java.util.List;

import com.haloofwar.engine.components.TransformComponent;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.CollisionEvent;
import com.haloofwar.engine.events.DamageEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.NewEntityEvent;
import com.haloofwar.engine.events.PeacefulEvent;
import com.haloofwar.engine.systems.EventSystem;
import com.haloofwar.game.components.CollisionComponent;
import com.haloofwar.game.components.EquipmentComponent;
import com.haloofwar.game.components.MeleeAttackComponent;
import com.haloofwar.game.components.MeleeWeaponComponent;
import com.haloofwar.interfaces.Updatable;

public class MeleeWeaponSystem extends EventSystem implements Updatable {

    private final EventBus bus;
    private final List<Entity> tempHitboxes = new ArrayList<>();
    private boolean peaceful = false;
    
    public MeleeWeaponSystem(EventBus bus) {
        this.bus = bus;
        this.listenerManager.add(bus, CollisionEvent.class, this::onCollision);
        this.listenerManager.add(bus, PeacefulEvent.class, this::onPeace);
    }

    @Override
    public void register(Entity e) {
        if (e.hasComponent(EquipmentComponent.class)) {
            EquipmentComponent equipment = e.getComponent(EquipmentComponent.class);
            if (equipment.currentWeapon != null && equipment.currentWeapon.hasComponent(MeleeWeaponComponent.class)) {
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
            if (!weaponEntity.hasComponent(MeleeWeaponComponent.class)) continue;

            MeleeWeaponComponent weapon = weaponEntity.getComponent(MeleeWeaponComponent.class);
            TransformComponent transform = entity.getComponent(TransformComponent.class);

            if (weapon.cooldownTimer > 0f) weapon.cooldownTimer -= delta;

            if (weapon.wantsToSwing && weapon.cooldownTimer <= 0f) {
                createHitbox(entity, weapon, transform);

                weapon.wantsToSwing = false;
                weapon.cooldownTimer = weapon.fireCooldown;
            }
        }
    }

    private void createHitbox(Entity source, MeleeWeaponComponent weapon, TransformComponent transform) {
        float centerX = transform.x + transform.width / 2;
        float centerY = transform.y + transform.height / 2;

        float hitboxWidth = weapon.range;
        float hitboxHeight = weapon.range;

        float hitboxX = centerX - hitboxWidth / 2;
        float hitboxY = centerY - hitboxHeight / 2;

        TransformComponent hitboxTransform = new TransformComponent(hitboxX, hitboxY, hitboxWidth, hitboxHeight);
        CollisionComponent hitboxCollision = new CollisionComponent(hitboxWidth, hitboxHeight, hitboxX, hitboxY);

        Entity hitbox = new Entity();
        hitbox.addComponent(hitboxTransform);
        hitbox.addComponent(hitboxCollision);
        hitbox.addComponent(new MeleeAttackComponent(source, weapon.damage));
        		
        tempHitboxes.add(hitbox);
        this.bus.publish(new NewEntityEvent(hitbox));
    }

    private void onCollision(CollisionEvent event) {
        // Aplicar daño si la entidad atacante es un hitbox melee
        if (event.a.hasComponent(MeleeAttackComponent.class)) {
            applyMeleeDamage(event.a, event.b);
        }
        if (event.b.hasComponent(MeleeAttackComponent.class)) {
            applyMeleeDamage(event.b, event.a);
        }
    }

    private void applyMeleeDamage(Entity attacker, Entity target) {
        MeleeAttackComponent attack = attacker.getComponent(MeleeAttackComponent.class);

        // Evitar que se dañe a sí mismo
        if (attack.source == target) {
        	return;
        }

        // Publicar daño
        this.bus.publish(new DamageEvent(target, attack.damage, attack.source));
    }

    // Método para que CollisionSystem elimine las hitboxes después de procesar colisiones
    public List<Entity> getTempHitboxes() {
        List<Entity> copy = new ArrayList<>(tempHitboxes);
        tempHitboxes.clear();
        return copy;
    }
    
    
    private void onPeace(PeacefulEvent event) {
    	this.peaceful = event.isPeaceful;
    }
}
