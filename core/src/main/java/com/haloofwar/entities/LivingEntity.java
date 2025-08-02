package com.haloofwar.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.components.animations.AnimationComponent;
import com.haloofwar.components.movement.MovementComponent;
import com.haloofwar.enumerators.entities.behavior.CollisionType;
import com.haloofwar.interfaces.Updatable;

public abstract class LivingEntity extends Entity implements Updatable {
    private final int DEFAULT_HEALTH = 100;
    private final int DEFAULT_VELOCITY = 150;

    protected int health;
    protected float velocity;
    protected boolean alive;

    protected final MovementComponent movement;
    protected final AnimationComponent animation;

    public LivingEntity(String name, MovementComponent movement, AnimationComponent animation, CollisionType type) {
        super(name, 32, 32, type);
        this.movement = movement;
        this.animation = animation;
        
        this.health = DEFAULT_HEALTH;
        this.velocity = DEFAULT_VELOCITY;
        this.alive = true;
    }
    
    @Override
    public void update(float delta) {
        if (!this.alive) {
        	return;
        }
        
        this.movement.update(delta, this.velocity);
        this.x = this.movement.getX();
        this.y = this.movement.getY();
        
        this.animation.update(delta, this.movement.getDirectionX(), this.movement.getDirectionY());
    }

    @Override
    public void render(SpriteBatch batch) {
        if (!this.alive) {
        	return;
        }
        
        this.animation.render(this.movement.getX(), this.movement.getY(), this.width, this.height, batch);
    }

    public void takeDamage(int amount) {
        this.health -= amount;
        if (this.health <= 0) {
            this.health = 0;
            this.alive = false;
        }
    }

    public int getHealth() {
        return this.health;
    }

    public boolean isAlive() {
        return this.alive;
    }
    
    public int getDEFAULT_HEALTH() {
  		return this.DEFAULT_HEALTH;
  	}
      
    public int getDEFAULT_VELOCITY() {
  		return this.DEFAULT_VELOCITY;
  	}
      
    public MovementComponent getMovement() {
  		return this.movement;
  	}
}
