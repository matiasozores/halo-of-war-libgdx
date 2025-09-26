package com.haloofwar.game.systems;

import com.haloofwar.engine.events.CollisionEvent;
import com.haloofwar.engine.events.EnterLevelEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.systems.EventSystem;
import com.haloofwar.game.components.PlayerComponent;
import com.haloofwar.game.components.PortalComponent;

public class PortalSystem extends EventSystem {
	private final EventBus bus;
	
	public PortalSystem(EventBus bus) {
		this.bus = bus;		
		this.listenerManager.add(bus, CollisionEvent.class, this::onCollision);
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
}
