package com.haloofwar.game.systems;

import com.haloofwar.common.enumerators.InventoryItemStatus;
import com.haloofwar.common.enumerators.SoundType;
import com.haloofwar.engine.events.CollisionEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.PlaySoundEvent;
import com.haloofwar.engine.events.RemoveEntityEvent;
import com.haloofwar.engine.events.UpdateInventoryEvent;
import com.haloofwar.engine.events.online.RemoveEntityEventOnline;
import com.haloofwar.engine.events.online.UpdateInventoryEventOnline;
import com.haloofwar.game.components.PlayerComponent;
import com.haloofwar.game.components.StockComponent;
import com.haloofwar.game.components.TransformComponent;

public class PickupSystem extends EventSystem {

	public PickupSystem(EventBus bus) {
		super(bus);
		this.listenerManager.add(bus, CollisionEvent.class, this::onCollision);
	}
	
	private void onCollision(CollisionEvent event) {
	    if(event.a.hasComponent(PlayerComponent.class) && event.b.hasComponent(StockComponent.class)) {
	    	PlayerComponent player = event.a.getComponent(PlayerComponent.class);
	        if(player.isInteracting) { 
	            this.bus.publish(new RemoveEntityEvent(event.b));
	            this.bus.publish(new PlaySoundEvent(SoundType.ITEM_PICKUP));
	            
	            StockComponent stock = event.b.getComponent(StockComponent.class);
	            TransformComponent transform = event.b.getComponent(TransformComponent.class);
	            TransformComponent playerTransform = event.a.getComponent(TransformComponent.class);
	            this.bus.publish(new UpdateInventoryEvent(playerTransform.identifier, event.b, 1, InventoryItemStatus.ADD));
	            this.bus.publish(new RemoveEntityEventOnline(transform.identifier));
	            this.bus.publish(new UpdateInventoryEventOnline(transform.identifier, player.type, stock.getType(), 1, InventoryItemStatus.ADD));
	        }
	    } else if(event.b.hasComponent(PlayerComponent.class) && event.a.hasComponent(StockComponent.class)) {
	    	PlayerComponent player = event.b.getComponent(PlayerComponent.class);
	        if(player.isInteracting) { 
	            this.bus.publish(new RemoveEntityEvent(event.a));
	            this.bus.publish(new PlaySoundEvent(SoundType.ITEM_PICKUP));
	            
	            StockComponent stock = event.a.getComponent(StockComponent.class);
	            TransformComponent transform = event.a.getComponent(TransformComponent.class);
	            TransformComponent playerTransform = event.b.getComponent(TransformComponent.class);
	            this.bus.publish(new UpdateInventoryEvent(playerTransform.identifier, event.b, 1, InventoryItemStatus.ADD));
	            this.bus.publish(new RemoveEntityEventOnline(transform.identifier));
	            this.bus.publish(new UpdateInventoryEventOnline(transform.identifier, player.type, stock.getType(), 1, InventoryItemStatus.ADD));
	        }
	    }
	}
}
