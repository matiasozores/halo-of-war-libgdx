package com.haloofwar.game.systems.weapons;

import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.online.MeleeAttackEventOnline;
import com.haloofwar.game.components.MeleeWeaponComponent;
import com.haloofwar.game.components.TransformComponent;

public class MeleeWeaponBehavior implements WeaponBehavior {

    @Override
    public void update(Entity owner, Entity weapon, float delta, EventBus bus) {
        MeleeWeaponComponent melee = weapon.getComponent(MeleeWeaponComponent.class);
        TransformComponent t = owner.getComponent(TransformComponent.class);

        if (melee.cooldownTimer > 0f) {
            melee.cooldownTimer -= delta;
            return;
        }

        if (!melee.wantsToSwing) {
        	return;
        }
        
        
        if(!owner.hasComponent(TransformComponent.class)) {
        	System.out.println("Ha ocurrido un problema al aplicar un ataque melee... | MeleeWeaponBehavior");
        	return;
        }
        
        final int identifier = owner.getComponent(TransformComponent.class).identifier;   
        
        melee.cooldownTimer = melee.fireCooldown;
        melee.wantsToSwing = false;
        bus.publish(new MeleeAttackEventOnline(identifier, t.x, t.y, t.width, t.height, melee.damage, melee.range));  
    }
}

