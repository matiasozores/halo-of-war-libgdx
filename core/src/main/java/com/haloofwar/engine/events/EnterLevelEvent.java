package com.haloofwar.engine.events;

import com.haloofwar.common.enums.LevelSceneType;

public class EnterLevelEvent {
	public final LevelSceneType type;
	
	public EnterLevelEvent(LevelSceneType type) {
		this.type = type;
	}
}
