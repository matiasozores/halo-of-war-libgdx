package com.haloofwar.ecs.systems.collision;

import com.haloofwar.dependences.input.InputManager;
import com.haloofwar.ecs.components.collision.PickupComponent;
import com.haloofwar.ecs.components.gameplay.InventoryComponent;
import com.haloofwar.ecs.events.CollisionEvent;
import com.haloofwar.ecs.events.EventBus;
import com.haloofwar.ecs.events.types.ItemPickedUpEvent;
import com.haloofwar.ecs.events.types.PlaySoundEvent;
import com.haloofwar.ecs.systems.ECSSystem;
import com.haloofwar.enumerators.game.SoundType;

public class PickupSystem implements ECSSystem{
	
	private final InputManager input;
	private final EventBus bus;
	
	public PickupSystem(EventBus bus, InputManager input) {
		this.input = input;
		this.bus = bus;		
		this.bus.subscribe(CollisionEvent.class, this::onCollision);
	}

	private void onCollision(CollisionEvent event) {
		if(event.a.hasComponent(InventoryComponent.class) && event.b.hasComponent(PickupComponent.class)) {
			PickupComponent pickup = event.b.getComponent(PickupComponent.class);
			
			if(this.input.isInteract() && !pickup.isPickedUp()) {
				pickup.setPickedUp(true);
				InventoryComponent inventory = event.a.getComponent(InventoryComponent.class);
				inventory.add(event.b);
				this.bus.publish(new ItemPickedUpEvent(event.b));
				this.bus.publish(new PlaySoundEvent(SoundType.LOAD_GAME));
			}
		}
		
	}
}
