package com.haloofwar.game.components;

import com.haloofwar.common.enums.PlayerType;
import com.haloofwar.engine.components.Component;

public class PlayerComponent implements Component{
	public PlayerType type;
	public boolean isInteracting = false;
	
	public PlayerComponent(PlayerType type) {
		this.type = type;
	}
}
