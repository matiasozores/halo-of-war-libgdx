package com.haloofwar.game.systems;

import com.haloofwar.common.enumerators.PlayerType;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.InteractEvent;
import com.haloofwar.engine.events.UpdateInventoryEvent;
import com.haloofwar.game.components.InventoryComponent;
import com.haloofwar.game.components.PlayerComponent;
import com.haloofwar.game.components.TransformComponent;

public class PlayerSystem extends EventSystem {
	public PlayerSystem(EventBus bus) {
		super(bus);
		this.listenerManager.add(bus, InteractEvent.class, this::onInteract);
		this.listenerManager.add(bus, UpdateInventoryEvent.class, this::onUpdateInventory);
	}
	
	@Override 
	public void register(Entity entity) {
		if(entity.hasComponent(PlayerComponent.class)) {
			this.ENTITIES.add(entity);
		}
	}
	
	private void onInteract(InteractEvent event) {
		boolean hasInteracted = false;
		int i = 0;
		
		while(!hasInteracted && i < this.ENTITIES.size()) {
			PlayerType type = this.ENTITIES.get(i).getComponent(PlayerComponent.class).type;
			
			if(type.equals(event.playerType)) {
				PlayerComponent playerComp = this.ENTITIES.get(i).getComponent(PlayerComponent.class);
				playerComp.isInteracting = event.isPressed();
				hasInteracted = true;
			} else {
				i++;	
			}
		}
	}
	
	private Entity getEntityByIdentifier(final int identifier) {
		boolean found = false;
		int i = 0;
		Entity entity = null;
		
		while(i < this.ENTITIES.size() && !found) {
			if(this.ENTITIES.get(i).hasComponent(TransformComponent.class)) {
				TransformComponent tc = this.ENTITIES.get(i).getComponent(TransformComponent.class);
				if(tc.identifier == identifier) {
					found = true;
					entity = this.ENTITIES.get(i);
				} else {
					i++;
				}
			} else {
				i++;
			}
		}
		
		return entity;
	}
	
	private void onUpdateInventory(UpdateInventoryEvent event) {
		Entity entity = this.getEntityByIdentifier(event.identifier);
		
		if(entity == null) {
			System.out.println("Ha ocurrido un problema al intentar agregar un objeto al inventario... | PlayerSystem");
			return;
		}
		
        InventoryComponent inventory = entity.getComponent(InventoryComponent.class);
        inventory.add(event.item, event.quantity);
	}
}
