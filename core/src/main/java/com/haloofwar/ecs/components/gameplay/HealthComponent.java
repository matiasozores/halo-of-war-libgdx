package com.haloofwar.ecs.components.gameplay;

import com.haloofwar.ecs.components.Component;

public class HealthComponent implements Component{
	public int maxHealth;
	public int currentHealth;
	
	public HealthComponent(int maxHealth) {
		this.maxHealth = maxHealth;
		this.currentHealth = this.maxHealth;
	}
}
