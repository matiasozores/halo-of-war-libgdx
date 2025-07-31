package com.haloofwar.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.components.AnimationComponent;
import com.haloofwar.components.movement.MovementComponent;
import com.haloofwar.dependences.collision.Collidable;
import com.haloofwar.enumerators.CollisionType;
import com.haloofwar.interfaces.Renderable;

public abstract class Entity implements Collidable, Renderable {
    private final int DEFAULT_HEALTH = 100;
    private final int DEFAULT_VELOCITY = 150;

    protected String name;
    protected int health;
    protected float velocity;
    protected boolean alive;
    protected int width = 32, height = 32;

    protected final MovementComponent movement;
    protected final AnimationComponent animation;
    protected final CollisionType type = CollisionType.ENTITY;

    public Entity(String name, MovementComponent movement, AnimationComponent animation) {
        this.name = name;
        this.health = DEFAULT_HEALTH;
        this.velocity = DEFAULT_VELOCITY;
        this.alive = true;
        this.movement = movement;
        this.animation = animation;
    }
    
    public void update(float delta) {
        if (!this.alive) {
        	return;
        }
        
        this.movement.update(delta, this.velocity);
        this.animation.update(delta, this.movement.getDirectionX(), this.movement.getDirectionY());
    }

    @Override
    public void render(SpriteBatch batch) {
        if (!alive) {
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

    public String getName() {
        return this.name;
    }

    public int getHealth() {
        return this.health;
    }

    public boolean isAlive() {
        return this.alive;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.movement.getX(), this.movement.getY(), this.width, this.height);
    }

    @Override
    public CollisionType getCollisionType() {
        return this.type;
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

    public void dispose() {
    }
    
  
}
