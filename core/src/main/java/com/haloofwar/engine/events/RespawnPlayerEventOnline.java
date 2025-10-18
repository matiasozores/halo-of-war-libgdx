package com.haloofwar.engine.events;

import com.haloofwar.common.enumerators.PlayerType;

public class RespawnPlayerEventOnline {
	public final PlayerType type;

	public RespawnPlayerEventOnline(PlayerType type) {
		this.type = type;
	}
}
