package com.haloofwar.game.systems;

import com.haloofwar.engine.events.CollisionEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.TalkEvent;
import com.haloofwar.engine.systems.EventSystem;
import com.haloofwar.game.components.DialogueComponent;
import com.haloofwar.game.components.PlayerComponent;

public class TalkSystem extends EventSystem {
	private final EventBus bus;

	public TalkSystem(EventBus bus) {
		this.bus = bus;		
		this.listenerManager.add(bus, CollisionEvent.class, this::onCollision);
	}

	private void onCollision(CollisionEvent event) {
		if(event.a.hasComponent(PlayerComponent.class) && event.b.hasComponent(DialogueComponent.class)) {
	        PlayerComponent player = event.a.getComponent(PlayerComponent.class);
	        DialogueComponent dialogue = event.b.getComponent(DialogueComponent.class);
	        
			if(player.isInteracting) {
				this.bus.publish(new TalkEvent(dialogue.lines, dialogue.avatar));
			}
		} else if(event.b.hasComponent(PlayerComponent.class) && event.a.hasComponent(DialogueComponent.class)) {
	        PlayerComponent player = event.b.getComponent(PlayerComponent.class);
	        DialogueComponent dialogue = event.a.getComponent(DialogueComponent.class);
	        
			if(player.isInteracting) {
				this.bus.publish(new TalkEvent(dialogue.lines, dialogue.avatar));
			}
		}
		
	}
}
