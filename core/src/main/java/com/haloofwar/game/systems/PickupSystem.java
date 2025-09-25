package com.haloofwar.game.systems;

import com.haloofwar.common.enums.SoundType;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.CollisionEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.PlaySoundEvent;
import com.haloofwar.engine.events.RemoveEntityEvent;
import com.haloofwar.engine.interfaces.Registrable;
import com.haloofwar.game.components.InventoryComponent;
import com.haloofwar.game.components.PlayerComponent;
import com.haloofwar.game.components.StockComponent;

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
	            this.bus.publish(new PlaySoundEvent(SoundType.ITEM_PICKUP));
	        }
	    } else if(event.b.hasComponent(PlayerComponent.class) && event.a.hasComponent(StockComponent.class)) {
	    	PlayerComponent player = event.b.getComponent(PlayerComponent.class);
	        if(player.isInteracting) { 
	            InventoryComponent inventory = event.b.getComponent(InventoryComponent.class);
	            inventory.add(event.a);
	            this.bus.publish(new RemoveEntityEvent(event.a));
	            this.bus.publish(new PlaySoundEvent(SoundType.ITEM_PICKUP));
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
