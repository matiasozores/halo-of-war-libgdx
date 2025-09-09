package com.haloofwar.components;

import com.haloofwar.interfaces.Component;

public class VisibilityComponent implements Component {
	private boolean isVisible;
	private float invisibleDuration;
	
	public VisibilityComponent() {
		this.isVisible = true;
	}
	
	public void setInvisible(float duration) {
		if(duration == 0f) {
			this.isVisible = true;
			this.invisibleDuration = duration;
			System.out.println("Set to visible");
		} else if(duration > 0f) {
			this.invisibleDuration = duration;
			this.isVisible = false;
			System.out.println("Set to invisible for " + duration + " seconds");
		} else {
			this.invisibleDuration = 0f;
			return;
		}
	}
	
	public boolean isVisible() {
		return this.isVisible;
	}
	
	public float getInvisibleDuration() {
		return this.invisibleDuration;
	}
}
