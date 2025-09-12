package com.haloofwar.events;

import com.haloofwar.enumerators.LevelType;

public class EnterLevelEvent {
	public final LevelType type;
	
	public EnterLevelEvent(LevelType type) {
		this.type = type;
	}
}
