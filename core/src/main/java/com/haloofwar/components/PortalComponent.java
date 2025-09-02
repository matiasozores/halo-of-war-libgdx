package com.haloofwar.components;

import com.haloofwar.enumerators.SceneType;
import com.haloofwar.interfaces.Component;

public class PortalComponent implements Component {
	public SceneType targetScene;
	
	public PortalComponent() {
		this.targetScene = SceneType.CAVE;
	}
}
