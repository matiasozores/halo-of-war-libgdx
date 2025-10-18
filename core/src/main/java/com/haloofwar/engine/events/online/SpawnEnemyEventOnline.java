package com.haloofwar.engine.events.online;

import com.haloofwar.common.enumerators.EnemyType;

public class SpawnEnemyEventOnline {
	public final int identifier;
	public final EnemyType type;
	public final float x, y;
	
	public SpawnEnemyEventOnline(int identifier, EnemyType type, float x, float y) {
		this.identifier = identifier;
		this.type = type;
		this.x = x;
		this.y = y;
	}
}
