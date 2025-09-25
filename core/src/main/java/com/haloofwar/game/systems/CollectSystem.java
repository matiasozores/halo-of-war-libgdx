package com.haloofwar.game.systems;

import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.CollisionEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.PowerUpCollectedEvent;
import com.haloofwar.engine.events.RemoveEntityEvent;
import com.haloofwar.engine.interfaces.Registrable;
import com.haloofwar.game.components.CollectComponent;
import com.haloofwar.game.components.PlayerComponent;

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
