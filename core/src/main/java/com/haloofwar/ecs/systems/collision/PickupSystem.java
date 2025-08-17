package com.haloofwar.ecs.systems.collision;

import com.haloofwar.ecs.Entity;
import com.haloofwar.ecs.components.collision.PickupComponent;
import com.haloofwar.ecs.components.gameplay.InventoryComponent;
import com.haloofwar.ecs.events.EventBus;
import com.haloofwar.ecs.events.types.collision.CollisionEvent;
import com.haloofwar.ecs.events.types.input.InteractEvent;
import com.haloofwar.ecs.events.types.items.ItemPickedUpEvent;
import com.haloofwar.interfaces.systems.Registrable;

public class PickupSystem implements Registrable{
	private final EventBus bus;
	private boolean interacting = false;
	
	public PickupSystem(EventBus bus) {
		this.bus = bus;		
		this.bus.subscribe(CollisionEvent.class, this::onCollision);
		this.bus.subscribe(InteractEvent.class, this::onInteract);
	}

	private void onCollision(CollisionEvent event) {
		if(event.a.hasComponent(InventoryComponent.class) && event.b.hasComponent(PickupComponent.class)) {
			PickupComponent pickup = event.b.getComponent(PickupComponent.class);

			if(this.interacting && !pickup.isPickedUp()) {
				pickup.setPickedUp(true);
				InventoryComponent inventory = event.a.getComponent(InventoryComponent.class);
				inventory.add(event.b);
				this.bus.publish(new ItemPickedUpEvent(event.b));
			}
		}
		
	}
	
	private void onInteract(InteractEvent event) {
		this.interacting = event.isPressed();
	}

	@Override
	public void register(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregister(Entity entity) {
		// TODO Auto-generated method stub
		
	}
}
