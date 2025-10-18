package com.haloofwar.engine.events;

import com.haloofwar.common.enumerators.PlayerType;

public class RespawnPlayerEvent {
	public final PlayerType type;

	public RespawnPlayerEvent(PlayerType type) {
		this.type = type;
	}	
}
