package com.haloofwar.game.components;

import com.haloofwar.engine.components.Component;

public class PromptComponent implements Component {
	public boolean active = false;
	public float offset;
	
	public PromptComponent(float offset) {
		this.offset = offset;
	}
	
	
}
