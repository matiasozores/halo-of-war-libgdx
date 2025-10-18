package com.haloofwar.engine.events;

import com.haloofwar.common.enumerators.LevelSceneType;

public class PortalChangeStateEvent {
	public final LevelSceneType type;

	public PortalChangeStateEvent(LevelSceneType type) {
		this.type = type;
	}
}
