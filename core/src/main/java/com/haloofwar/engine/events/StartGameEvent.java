package com.haloofwar.engine.events;

import com.haloofwar.common.enumerators.PlayerType;

public class StartGameEvent {
	public final int kratosId;
	public final int masterchiefId;
	public final PlayerType playerType;
	
	public StartGameEvent(final int kratosId, final int masterchiefId, PlayerType playerType) {
		this.playerType = playerType;
		this.kratosId = kratosId;
		this.masterchiefId = masterchiefId;
	}
}
