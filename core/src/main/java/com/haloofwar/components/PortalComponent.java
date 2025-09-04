package com.haloofwar.components;

import com.haloofwar.enumerators.LevelType;
import com.haloofwar.interfaces.Component;

public class PortalComponent implements Component {
	public LevelType targetScene;
	
	public PortalComponent(String targetScene) {
		this.targetScene = LevelType.getLevelByName(targetScene);
	}
}
