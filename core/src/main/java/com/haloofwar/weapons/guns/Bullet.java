package com.haloofwar.weapons.guns;

import com.badlogic.gdx.Gdx;

public class Bullet {
	private float x, y;
	private float dirX, dirY;
	private int damage;
	private float speed;
	private boolean isActive = true;
	
	private int width = 5, height = 5; 
	
	public Bullet(float startX, float startY, float targetX, float targetY, int damage, float speed) {
		this.x = startX;
		this.y = startY;
		this.damage = damage;
		this.speed = speed;

		float dx = targetX - startX;
		float dy = targetY - startY;
		float length = (float) Math.sqrt(dx * dx + dy * dy);
		
		// Normalizar dirección
		this.dirX = dx / length;
		this.dirY = dy / length;
	}
	
	public void update() {
		if (!this.isActive) return;

		this.x += dirX * speed;
		this.y += dirY * speed;
		
		if (x < 0 || y < 0 || x > Gdx.graphics.getWidth() || y > Gdx.graphics.getHeight()) {
			this.isActive = false;
		}
	}
	
	public void render() {
		if (!this.isActive) return;
		
		
	}
	
	public boolean isActive() {
		return this.isActive;
	}
}
