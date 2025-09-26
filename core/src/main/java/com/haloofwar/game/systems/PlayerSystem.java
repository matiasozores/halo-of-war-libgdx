package com.haloofwar.game.systems;

import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.InteractEvent;
import com.haloofwar.engine.events.NewPlayerEvent;
import com.haloofwar.engine.systems.EventSystem;
import com.haloofwar.game.components.PlayerComponent;

public class PlayerSystem extends EventSystem {
	private Entity player;
	
	public PlayerSystem(Entity player, EventBus bus) {
		if(!player.hasComponent(PlayerComponent.class)) {
			System.out.println("No se puede crear el sistema del jugador porque se paso una entidad que no lo es...");
			this.player = null;
		} else {
			this.player = player;
		}
		
		this.listenerManager.add(bus, NewPlayerEvent.class, this::onNewPlayer);
		this.listenerManager.add(bus, InteractEvent.class, this::onInteract);
	}
	
	private void onInteract(InteractEvent event) {
		if(this.player != null) {
			this.player.getComponent(PlayerComponent.class).isInteracting = event.isPressed();
		}
	}

	private void onNewPlayer(NewPlayerEvent event) {
		if(event.player.hasComponent(PlayerComponent.class)) {
			this.player = event.player;
		} else {
			System.out.println("No se puede asignar nuevo jugador porque no lo es... | PlayerSystem");
		}
	}
}
