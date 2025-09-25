package com.haloofwar.engine.events;

import com.haloofwar.engine.entity.Entity;

public class NewPlayerEvent {
	public final Entity player;

	public NewPlayerEvent(Entity player) {
		this.player = player;
	}
}
