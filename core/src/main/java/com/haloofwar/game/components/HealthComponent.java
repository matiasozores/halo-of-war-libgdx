package com.haloofwar.game.components;

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
        if (amount == 0) return;

        if (amount > 0) {
            if (this.shield > 0) {
                if (amount <= this.shield) {
                    this.shield -= amount;
                    amount = 0;
                } else {
                    amount -= this.shield;
                    this.shield = 0;
                }
            }

            if (amount > 0) {
                this.currentHealth -= amount;
            }
        } 

        else {
            this.currentHealth -= amount; 
        }

        if (this.currentHealth <= 0) {
            this.currentHealth = 0;
            this.alive = false;
        } else if (this.currentHealth > this.maxHealth) {
            this.currentHealth = this.maxHealth;
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
    
    public boolean reset() {
    	boolean respawn = !this.alive;
    	
    	this.currentHealth = this.maxHealth;
    	this.alive = true;
    	
    	return respawn;
    }
}