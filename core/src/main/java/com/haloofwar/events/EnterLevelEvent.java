package com.haloofwar.events;

import com.haloofwar.components.Entity;

public class EnterLevelEvent {
	public final Entity portal;
	
	public EnterLevelEvent(Entity portal) {
		this.portal = portal;
	}
}
