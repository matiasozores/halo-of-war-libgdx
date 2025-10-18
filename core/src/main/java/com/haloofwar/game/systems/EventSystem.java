package com.haloofwar.game.systems;

import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.EventListenerManager;

public class EventSystem extends BaseSystem {
	
	protected final EventListenerManager listenerManager = new EventListenerManager();
	
	public EventSystem(EventBus bus) {
		super(bus);
	}
	
	@Override
	public void dispose() {
		super.dispose(); 
		this.listenerManager.clear();
	}
}
