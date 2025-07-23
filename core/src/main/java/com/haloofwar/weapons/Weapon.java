package com.haloofwar.weapons;

public abstract class Weapon {	
	private String name;
	protected int damage;
	private int cooldown;
	private int cooldownCounter = 0;
	protected boolean isReady = true;
	
	public Weapon(String name, int damage, int cooldown) {
		this.name = name;
		this.damage = damage;
		this.cooldown = cooldown;
	}
	
	public abstract void use(int playerX, int playerY);

	public void update() {
		if(!this.isReady) {
			this.cooldownCounter --;
			if(this.cooldownCounter <= 0) {
				this.isReady = true;
				this.cooldownCounter = 0;
			}
		}
	}
	
	public void render() {
		
	}
	
	protected void resetCooldown() {
		this.isReady = false;
		this.cooldownCounter = this.cooldown;
	}
}
