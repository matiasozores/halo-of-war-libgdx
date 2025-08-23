package com.haloofwar.systems;

import com.haloofwar.components.Entity;
import com.haloofwar.components.HealthComponent;
import com.haloofwar.components.PlayerComponent;
import com.haloofwar.events.DamageEvent;
import com.haloofwar.events.EventBus;
import com.haloofwar.events.PlayerDiedEvent;
import com.haloofwar.interfaces.Registrable;

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
