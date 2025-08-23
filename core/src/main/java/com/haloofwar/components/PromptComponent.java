package com.haloofwar.components;

import com.haloofwar.interfaces.Component;

public class PromptComponent implements Component {
	public boolean active = false;
	public float offset;
	
	public PromptComponent(float offset) {
		this.offset = offset;
	}
	
	
}
