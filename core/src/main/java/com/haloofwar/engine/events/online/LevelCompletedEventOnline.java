package com.haloofwar.engine.events.online;

import com.haloofwar.common.enumerators.LevelSceneType;

public class LevelCompletedEventOnline {
	public final LevelSceneType levelType;

	public LevelCompletedEventOnline(LevelSceneType levelType) {
		this.levelType = levelType;
	}
}
