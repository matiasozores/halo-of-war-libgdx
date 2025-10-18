package com.haloofwar.engine.events;

import com.haloofwar.common.enumerators.PlayerType;

public class SetCanMoveEvent {
	public final PlayerType playerType;
	public final boolean canMove;
	
	public SetCanMoveEvent(PlayerType playerType, boolean canMove) {
		this.playerType = playerType;
		this.canMove = canMove;
	}
}
