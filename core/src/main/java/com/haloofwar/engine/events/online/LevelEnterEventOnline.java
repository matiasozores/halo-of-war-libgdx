package com.haloofwar.engine.events.online;

import com.haloofwar.common.enumerators.LevelSceneType;

public class LevelEnterEventOnline {
	public final LevelSceneType type;

	public LevelEnterEventOnline(LevelSceneType type) {
		this.type = type;
	}		
}
