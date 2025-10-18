package com.haloofwar.engine.events;

import com.haloofwar.engine.entity.Entity;

public class PlayerDiedEvent {
	public final Entity player;

	public PlayerDiedEvent(Entity player) {
		this.player = player;
	}
}
