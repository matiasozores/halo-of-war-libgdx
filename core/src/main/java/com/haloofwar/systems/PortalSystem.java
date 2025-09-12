package com.haloofwar.systems;

import com.haloofwar.components.Entity;
import com.haloofwar.components.PlayerComponent;
import com.haloofwar.components.PortalComponent;
import com.haloofwar.events.CollisionEvent;
import com.haloofwar.events.EnterLevelEvent;
import com.haloofwar.events.EventBus;
import com.haloofwar.interfaces.Registrable;

public class PortalSystem implements Registrable {
	private final EventBus bus;
	
	public PortalSystem(EventBus bus) {
		this.bus = bus;		
		this.bus.subscribe(CollisionEvent.class, this::onCollision);
	}
	
	private void onCollision(CollisionEvent event) {
	    if(event.a.hasComponent(PlayerComponent.class) && event.b.hasComponent(PortalComponent.class)) {
	        PlayerComponent player = event.a.getComponent(PlayerComponent.class);
	        PortalComponent portal = event.b.getComponent(PortalComponent.class);
	        if(player.isInteracting) { 
	            this.bus.publish(new EnterLevelEvent(portal.targetScene));
	        }
	    }
	}
	
	@Override
	public void register(Entity entity) {
	}

	@Override
	public void unregister(Entity entity) {
	}
}
