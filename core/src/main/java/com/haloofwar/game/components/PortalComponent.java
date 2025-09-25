package com.haloofwar.game.components;

import com.haloofwar.common.enums.LevelSceneType;
import com.haloofwar.engine.components.Component;

public class PortalComponent implements Component {
	public LevelSceneType targetScene;
	
	public PortalComponent(String targetScene) {
		this.targetScene = LevelSceneType.getLevelByName(targetScene);
	}
}
