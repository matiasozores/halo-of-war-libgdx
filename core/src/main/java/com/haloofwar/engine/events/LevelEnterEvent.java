package com.haloofwar.engine.events;

import com.haloofwar.common.enumerators.LevelSceneType;

public class LevelEnterEvent {
	public final LevelSceneType type;

	public LevelEnterEvent(LevelSceneType type) {
		this.type = type;
	}
}
