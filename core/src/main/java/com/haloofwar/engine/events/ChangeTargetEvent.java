package com.haloofwar.engine.events;

import com.haloofwar.engine.entity.Entity;

public class ChangeTargetEvent {
	public final Entity newTarget;

	public ChangeTargetEvent(Entity newTarget) {
		this.newTarget = newTarget;
	}
	
}
