package com.haloofwar.game.components;

import com.haloofwar.common.enumerators.LevelSceneType;

public class PortalComponent implements Component {
	public LevelSceneType targetScene;
	
	public PortalComponent(String targetScene) {
		this.targetScene = LevelSceneType.getLevelByName(targetScene);
	}
}
