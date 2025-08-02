package com.haloofwar.entities;

import com.haloofwar.enumerators.entities.behavior.CollisionType;

public abstract class StaticEntity extends Entity{
	public StaticEntity(String name, int width, int height, CollisionType collisionType) {
		super(name, width, height, collisionType);
	}
	
	public StaticEntity(String name, float x, float y, int width, int height, CollisionType collisionType) {
		super(name, x, y, width, height, collisionType);
	}
}
