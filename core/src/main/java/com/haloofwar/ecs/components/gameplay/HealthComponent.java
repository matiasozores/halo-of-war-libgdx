package com.haloofwar.ecs.components.gameplay;

import com.haloofwar.ecs.components.Component;

public class HealthComponent implements Component{
	private int maxHealth;
	private int currentHealth;
	public boolean alive = true;
	
	public HealthComponent(int maxHealth) {
		this.maxHealth = maxHealth;
		this.currentHealth = this.maxHealth;
	}
	
	public void affectHealth(int amount) {
		if(amount > 0) {
			amount = -amount;
		}
		
		if(this.currentHealth + amount <= 0) {
			this.currentHealth = 0;
			this.alive = false;
		} else {
			this.currentHealth += amount;
		}
	}
	
	public int getCurrentHealth() {
		return this.currentHealth;
	}
	
	public int getMaxHealth() {
		return this.maxHealth;
	}
	
	public boolean isAlive() {
		return this.alive;
	}
}
