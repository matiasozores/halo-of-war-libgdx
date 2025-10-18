package com.haloofwar.engine.events;

import com.haloofwar.common.enumerators.LevelSceneType;

public class UnlockLevelEvent {
	public final LevelSceneType type;

	public UnlockLevelEvent(LevelSceneType type) {
		this.type = type;
	}
}
