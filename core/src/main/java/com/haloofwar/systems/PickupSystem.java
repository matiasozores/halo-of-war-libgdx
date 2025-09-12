package com.haloofwar.systems;

import com.haloofwar.components.Entity;
import com.haloofwar.components.InventoryComponent;
import com.haloofwar.components.PlayerComponent;
import com.haloofwar.components.StockComponent;
import com.haloofwar.events.CollisionEvent;
import com.haloofwar.events.EventBus;
import com.haloofwar.events.PickupSoundEvent;
import com.haloofwar.events.RemoveEntityEvent;
import com.haloofwar.interfaces.Registrable;

public class PickupSystem implements Registrable {
	private final EventBus bus;
	
	public PickupSystem(EventBus bus) {
		this.bus = bus;		
		this.bus.subscribe(CollisionEvent.class, this::onCollision);
	}
	
	private void onCollision(CollisionEvent event) {
	    if(event.a.hasComponent(PlayerComponent.class) && event.b.hasComponent(StockComponent.class)) {
	        PlayerComponent player = event.a.getComponent(PlayerComponent.class);
	        if(player.isInteracting) { 
	            InventoryComponent inventory = event.a.getComponent(InventoryComponent.class);
	            inventory.add(event.b);
	            this.bus.publish(new RemoveEntityEvent(event.b));
	            this.bus.publish(new PickupSoundEvent());
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
