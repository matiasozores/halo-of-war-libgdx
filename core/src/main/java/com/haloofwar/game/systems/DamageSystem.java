package com.haloofwar.game.systems;

import com.haloofwar.common.enumerators.SoundType;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.DamageEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.PlaySoundEvent;
import com.haloofwar.engine.events.RemoveEntityEvent;
import com.haloofwar.engine.events.online.RemoveEntityEventOnline;
import com.haloofwar.game.components.HealthComponent;
import com.haloofwar.game.components.TransformComponent;

public class DamageSystem extends EventSystem {

    public DamageSystem(EventBus bus) {
        super(bus);
        this.listenerManager.add(bus, DamageEvent.class, this::onDamage);
    }

    private void onDamage(DamageEvent event) {
        Entity target = event.target;

        if (!target.hasComponent(HealthComponent.class)) {
        	return;
        }

        HealthComponent health = target.getComponent(HealthComponent.class);
        System.out.print("Ha llegado un daño de: " + event.amount + " | ");
        System.out.print("Se ha causado daño a una entidad: DE " + health.getCurrentHealth() + " A ");
        health.affectHealth(event.amount);
        System.out.println(health.getCurrentHealth() + " de vida");
        this.bus.publish(new PlaySoundEvent(SoundType.HIT));
        
        

        if (!health.isAlive()) {
        	this.bus.publish(new RemoveEntityEvent(target));
        	
        	TransformComponent tc = target.getComponent(TransformComponent.class);
        	this.bus.publish(new RemoveEntityEventOnline(tc.identifier));
        }
    }
}
