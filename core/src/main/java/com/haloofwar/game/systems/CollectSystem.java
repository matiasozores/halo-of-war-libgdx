package com.haloofwar.game.systems;

import com.haloofwar.engine.events.CollisionEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.PowerUpCollectedEvent;
import com.haloofwar.engine.events.RemoveEntityEvent;
import com.haloofwar.engine.events.online.RemoveEntityEventOnline;
import com.haloofwar.engine.interfaces.Registrable;
import com.haloofwar.game.components.CollectComponent;
import com.haloofwar.game.components.PlayerComponent;
import com.haloofwar.game.components.TransformComponent;

public class CollectSystem extends EventSystem implements Registrable {
	private final EventBus bus;
	
	public CollectSystem(EventBus bus) {
		super(bus);
		this.bus = bus;		
		this.listenerManager.add(bus, CollisionEvent.class, this::onCollision);
	}
	
	private void onCollision(CollisionEvent event) {
	    if(event.a.hasComponent(PlayerComponent.class) && event.b.hasComponent(CollectComponent.class)) {
	        this.bus.publish(new PowerUpCollectedEvent(event.a, event.b));
	        this.bus.publish(new RemoveEntityEvent(event.b));
	        
	        TransformComponent tc = event.b.getComponent(TransformComponent.class);
	        this.bus.publish(new RemoveEntityEventOnline(tc.identifier));
	    }
	}
}
