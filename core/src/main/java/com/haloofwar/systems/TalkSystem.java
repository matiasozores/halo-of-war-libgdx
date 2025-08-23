package com.haloofwar.systems;

import com.haloofwar.components.DialogueComponent;
import com.haloofwar.components.Entity;
import com.haloofwar.components.PlayerComponent;
import com.haloofwar.events.CollisionEvent;
import com.haloofwar.events.EventBus;
import com.haloofwar.events.TalkEvent;
import com.haloofwar.interfaces.Registrable;

public class TalkSystem implements Registrable{
	private final EventBus bus;

	public TalkSystem(EventBus bus) {
		this.bus = bus;		
		this.bus.subscribe(CollisionEvent.class, this::onCollision);
	}

	private void onCollision(CollisionEvent event) {
		if(event.a.hasComponent(PlayerComponent.class) && event.b.hasComponent(DialogueComponent.class)) {
	        PlayerComponent player = event.a.getComponent(PlayerComponent.class);
	        DialogueComponent dialogue = event.b.getComponent(DialogueComponent.class);
	        
			if(player.isInteracting) {
				this.bus.publish(new TalkEvent(dialogue.lines, dialogue.avatar));
			}
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
