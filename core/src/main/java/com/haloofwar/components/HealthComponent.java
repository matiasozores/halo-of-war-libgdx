package com.haloofwar.components;

import com.haloofwar.interfaces.Component;

public class HealthComponent implements Component{
    private int maxHealth;
    private int currentHealth;
    private float shield;

    public boolean alive = true;

    public HealthComponent(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = this.maxHealth;
    }

    public void affectHealth(int amount) {
        if(amount > 0) { 
            amount = -amount;
        }

        if(this.shield > 0) {
            this.shield -= amount; 
            if(this.shield < 0) {
                this.currentHealth += this.shield;
                this.shield = 0;
            }
        } else {
            this.currentHealth += amount;
        }

        if(this.currentHealth <= 0) {
            this.alive = false;
        }
    }

    public int getCurrentHealth() {
        return this.currentHealth;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }
    
    public float getShield() {
		return this.shield;
	}

    public void setShield(float shield) {
    	if(this.shield > 0) {
    		this.shield += shield;
    	} else {
			this.shield = shield;
		}
	}
    
    public boolean isAlive() {
        return this.alive;
    }
}