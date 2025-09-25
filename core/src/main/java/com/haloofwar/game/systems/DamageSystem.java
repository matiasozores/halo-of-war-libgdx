package com.haloofwar.game.systems;

import com.haloofwar.common.enums.SoundType;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.DamageEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.PlaySoundEvent;
import com.haloofwar.engine.events.PlayerDiedEvent;
import com.haloofwar.engine.events.RemoveEntityEvent;
import com.haloofwar.engine.interfaces.Registrable;
import com.haloofwar.game.components.HealthComponent;
import com.haloofwar.game.components.PlayerComponent;

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
        this.bus.publish(new PlaySoundEvent(SoundType.HIT));
        
        if (!health.isAlive()) {
        	this.bus.publish(new RemoveEntityEvent(target));
        	
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
