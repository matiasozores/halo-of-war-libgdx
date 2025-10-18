package com.haloofwar.engine.events;

import com.haloofwar.common.enumerators.EnemyType;

public class SpawnEnemyEvent {
	public final int IDENTIFIER;
	public final EnemyType type;
	public float x, y;
	
	public SpawnEnemyEvent(int IDENTIFIER, EnemyType type, float x, float y) {
		this.IDENTIFIER = IDENTIFIER;
		this.type = type;
		this.x = x;
		this.y = y;
	}
}
