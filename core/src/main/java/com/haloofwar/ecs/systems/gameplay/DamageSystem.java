package com.haloofwar.ecs.systems.gameplay;

import com.haloofwar.ecs.Entity;
import com.haloofwar.ecs.components.collision.PlayerComponent;
import com.haloofwar.ecs.components.gameplay.HealthComponent;
import com.haloofwar.ecs.events.EventBus;
import com.haloofwar.ecs.events.types.gameplay.DamageEvent;
import com.haloofwar.ecs.events.types.gameplay.PlayerDiedEvent;
import com.haloofwar.interfaces.systems.Registrable;

public class DamageSystem implements Registrable{
	private final EventBus bus;

    public DamageSystem(EventBus bus) {
        this.bus = bus;
        this.bus.subscribe(DamageEvent.class, this::onDamage);
    }

    private void onDamage(DamageEvent event) {
        Entity target = event.target;

        if (!target.hasComponent(HealthComponent.class)) {
        	return;
        }

        HealthComponent health = target.getComponent(HealthComponent.class);
        health.affectHealth(event.amount);
        
        if (!health.isAlive()) {
            if (target.hasComponent(PlayerComponent.class)) {
                this.bus.publish(new PlayerDiedEvent(target));
            } 
        }

    }

    // Se implementa a Registrable aunque no requiera sus metodos para que sea tratado como un sistema
    
	@Override
	public void register(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregister(Entity entity) {
		// TODO Auto-generated method stub
		
	}
}
