package com.haloofwar.engine.systems;

import com.haloofwar.engine.events.EventListenerManager;

public class EventSystem extends BaseSystem {
	protected final EventListenerManager listenerManager = new EventListenerManager();

	@Override
	public void dispose() {
		super.dispose(); 
		this.listenerManager.clear();
	}
}
