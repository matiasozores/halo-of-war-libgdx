package com.haloofwar.engine.events.online;

public class UpdateEntityPositionEventOnline {
	public final int identifier;
	public float x;
	public float y;
	public float dirX;
	public float dirY;
	
	public UpdateEntityPositionEventOnline(int identifier, float x, float y, float dirX, float dirY) {
		this.identifier = identifier;
		this.x = x;
		this.y = y;
		this.dirX = dirX;
		this.dirY = dirY;
	}
}
