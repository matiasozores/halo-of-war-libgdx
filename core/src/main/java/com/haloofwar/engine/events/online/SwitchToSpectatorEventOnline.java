package com.haloofwar.engine.events.online;

import com.haloofwar.common.enumerators.PlayerType;

public class SwitchToSpectatorEventOnline {
	public final PlayerType type;

	public SwitchToSpectatorEventOnline(PlayerType type) {
		this.type = type;
	}	
}
