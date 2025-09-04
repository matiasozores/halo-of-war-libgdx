package com.haloofwar.systems;

import com.haloofwar.components.Entity;
import com.haloofwar.components.PlayerComponent;
import com.haloofwar.events.EventBus;
import com.haloofwar.events.InteractEvent;
import com.haloofwar.interfaces.Registrable;

public class PlayerSystem implements Registrable {
	private final Entity player;
	
	/*
	 * 
	 * Temporal hasta entender como hacer los dos clientes y el servidor, ahora mismo no se puede diferenciar
	 * el jugador en el caso que haya dos jugadores
	 * 
	 * */
	
	public PlayerSystem(Entity player, EventBus bus) {
		if(!player.hasComponent(PlayerComponent.class)) {
			System.out.println("No se puede crear el sistema del juagdor porque se paso una entidad que no lo es...");
			this.player = null;
		} else {
			this.player = player;
		}
		
		bus.subscribe(InteractEvent.class, this::onInteract);
	}
	
	private void onInteract(InteractEvent event) {
		if(this.player != null) {
			this.player.getComponent(PlayerComponent.class).isInteracting = event.isPressed();
		}
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
