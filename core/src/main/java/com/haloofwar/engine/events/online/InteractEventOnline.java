package com.haloofwar.engine.events.online;

import com.haloofwar.common.enumerators.PlayerType;

public class InteractEventOnline {
	public PlayerType type;
	public boolean isPressed;
	
	public InteractEventOnline(PlayerType type, boolean isPressed) {
		this.type = type;
		this.isPressed = isPressed;
	}
}
