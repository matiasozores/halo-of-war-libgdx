package com.haloofwar.game.systems;

import com.haloofwar.common.enumerators.NPCType;
import com.haloofwar.common.enumerators.PlayerType;
import com.haloofwar.engine.events.CollisionEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.TalkEvent;
import com.haloofwar.engine.events.online.TalkEventOnline;
import com.haloofwar.game.components.DialogueComponent;
import com.haloofwar.game.components.PlayerComponent;
import com.haloofwar.game.components.VillagerComponent;

public class TalkSystem extends EventSystem {

	public TalkSystem(EventBus bus) {
		super(bus);
		this.listenerManager.add(bus, CollisionEvent.class, this::onCollision);
	}

	private void onCollision(CollisionEvent event) {
		PlayerType playerType;
		NPCType npcType;
		
		if(event.a.hasComponent(PlayerComponent.class) && event.b.hasComponent(DialogueComponent.class)) {
	        PlayerComponent player = event.a.getComponent(PlayerComponent.class);
	        playerType = player.type;
	        VillagerComponent villager = event.b.getComponent(VillagerComponent.class);
	        npcType = villager.npcType;
	        DialogueComponent dialogue = event.b.getComponent(DialogueComponent.class);
	        
			if(player.isInteracting) {
				this.bus.publish(new TalkEvent(playerType, dialogue.lines, dialogue.avatar));
				this.bus.publish(new TalkEventOnline(playerType, npcType));
			}
		} else if(event.b.hasComponent(PlayerComponent.class) && event.a.hasComponent(DialogueComponent.class)) {
	        PlayerComponent player = event.b.getComponent(PlayerComponent.class);
	        playerType = player.type;
	        VillagerComponent villager = event.b.getComponent(VillagerComponent.class);
	        npcType = villager.npcType;
	        DialogueComponent dialogue = event.b.getComponent(DialogueComponent.class);
	        
			if(player.isInteracting) {
				this.bus.publish(new TalkEvent(playerType, dialogue.lines, dialogue.avatar));
				this.bus.publish(new TalkEventOnline(playerType, npcType));
			}
		}
		
	}
}
