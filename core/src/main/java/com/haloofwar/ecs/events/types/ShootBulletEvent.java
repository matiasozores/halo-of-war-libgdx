package com.haloofwar.ecs.events.types;

public class ShootBulletEvent {
	public float x;
	public float y;
	public float dirX;
	public float dirY;
	public int damage;
	public int speed;
	
	public ShootBulletEvent(float x, float y, float dirX, float dirY, int damage, int speed) {
		this.x = x;
		this.y = y;
		this.dirX = dirX;
		this.dirY = dirY;
		this.damage = damage;
		this.speed = speed;
	}
}
