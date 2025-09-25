package com.haloofwar.game.systems;

import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.CollisionEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.TalkEvent;
import com.haloofwar.engine.interfaces.Registrable;
import com.haloofwar.game.components.DialogueComponent;
import com.haloofwar.game.components.PlayerComponent;

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
		} else if(event.b.hasComponent(PlayerComponent.class) && event.a.hasComponent(DialogueComponent.class)) {
	        PlayerComponent player = event.b.getComponent(PlayerComponent.class);
	        DialogueComponent dialogue = event.a.getComponent(DialogueComponent.class);
	        
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
