package com.haloofwar.engine.events.online;

public class RemotePositionUpdateEventOnline {
	public final int identifier;
	public float x, y;
	public float dirX, dirY;
	
	public RemotePositionUpdateEventOnline(final int identifier, float x, float y, float dirX, float dirY) {
		this.identifier = identifier;
		this.x = x;
		this.y = y;
		this.dirX = dirX;
		this.dirY = dirY;
	}
}
