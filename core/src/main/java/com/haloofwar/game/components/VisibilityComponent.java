package com.haloofwar.game.components;

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
		} else if(duration > 0f) {
			this.invisibleDuration = duration;
			this.isVisible = false;
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
