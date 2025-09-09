package com.haloofwar.systems;

import com.haloofwar.components.CollectComponent;
import com.haloofwar.components.Entity;
import com.haloofwar.components.PlayerComponent;
import com.haloofwar.events.CollisionEvent;
import com.haloofwar.events.EventBus;
import com.haloofwar.events.PowerUpCollectedEvent;
import com.haloofwar.events.RemoveEntityEvent;
import com.haloofwar.interfaces.Registrable;

public class CollectSystem implements Registrable {
	private final EventBus bus;
	
	public CollectSystem(EventBus bus) {
		this.bus = bus;		
		this.bus.subscribe(CollisionEvent.class, this::onCollision);
	}
	
	private void onCollision(CollisionEvent event) {
	    if(event.a.hasComponent(PlayerComponent.class) && event.b.hasComponent(CollectComponent.class)) {
	        this.bus.publish(new PowerUpCollectedEvent(event.a, event.b));
	        this.bus.publish(new RemoveEntityEvent(event.b));
	    }
	}
	
	@Override
	public void register(Entity entity) {
	}

	@Override
	public void unregister(Entity entity) {
	}
}
