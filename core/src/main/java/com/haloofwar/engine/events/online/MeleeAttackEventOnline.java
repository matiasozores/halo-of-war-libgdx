package com.haloofwar.engine.events.online;

public class MeleeAttackEventOnline {
	public final int identifier;
	public float x, y;
	public float width, height;
	public int damage;
	public float range;
	
	public MeleeAttackEventOnline(final int identifier, float x, float y, float width, float height, int damage, float range) {
		this.identifier = identifier;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.damage = damage;
		this.range = range;
	}
}
