package com.haloofwar.components;

import com.haloofwar.enumerators.PlayerType;
import com.haloofwar.interfaces.Component;

public class PlayerComponent implements Component{
	public PlayerType type;
	public boolean isInteracting = false;
	
	public PlayerComponent(PlayerType type) {
		this.type = type;
	}
}
