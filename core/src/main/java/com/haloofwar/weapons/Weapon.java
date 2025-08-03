package com.haloofwar.weapons;

import com.haloofwar.dependences.input.InputManager;

public abstract class Weapon {	
	private final int COOLDOWN = 60;
	
	private String name;
	protected int damage;
	protected int cooldown = this.COOLDOWN;
	protected boolean isReady = true;
	protected int speed;
	
	// Dependencias
	private InputManager input;
	
	public Weapon(String name, int damage, int speed, int cooldown, InputManager input) {
		this.name = name;
		this.damage = damage;
		this.cooldown = cooldown;
		this.input = input;
		this.speed = speed;
	}
	
	public void update(float delta, float playerX, float playerY, float mouseX, float mouseY) {
		if(!this.isReady) {
			this.cooldown --;
			if(this.cooldown <= 0) {
				this.isReady = true;
				this.cooldown = 0;
			}
		}
		
		if(input.isAttack() && this.isReady) {
			this.use(playerX, playerY, mouseX, mouseY);
	    }
	}
	
	protected abstract void use(float playerX, float playerY, float mouseX, float mouseY);
	
	protected void resetCooldown() {
		this.isReady = false;
		this.cooldown = this.COOLDOWN;
	}
	
	public String getName() {
		return this.name;
	}
}
