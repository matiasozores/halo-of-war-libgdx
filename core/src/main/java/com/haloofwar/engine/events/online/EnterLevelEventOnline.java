package com.haloofwar.engine.events.online;

import com.haloofwar.common.enumerators.LevelSceneType;

public class EnterLevelEventOnline {
	public final LevelSceneType type;

	public EnterLevelEventOnline(LevelSceneType type) {
		this.type = type;
	}

}
