package com.haloofwar.engine.events.online;

import com.haloofwar.common.enumerators.ObjectType;

public class SpawnItemEventOnline {
	public final int identifier;
	public final ObjectType item;
	public final float x;
	public final float y;
	
	public SpawnItemEventOnline(final int identifier, ObjectType item, float x, float y) {
		this.identifier = identifier;
		this.item = item;
		this.x = x;
		this.y = y;
	}
}
