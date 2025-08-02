package com.haloofwar.entities;

import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.dependences.collision.Collidable;
import com.haloofwar.enumerators.entities.behavior.CollisionType;
import com.haloofwar.interfaces.Renderable;

public abstract class Entity implements Collidable, Renderable {
	private String name;
	protected int width, height;
	protected float x, y;
	protected CollisionType collisionType;
	
    public Entity(String name, int width, int height, CollisionType collisionType) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.collisionType = collisionType;
    }
    
    public Entity(String name, float x, float y, int width, int height, CollisionType collisionType) {
	    this(name, width, height, collisionType);
	    this.x = x;
	    this.y = y;
    }
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
	
    @Override
    public CollisionType getCollisionType() {
        return this.collisionType;
    }
    
    public String getName() {
		return this.name;
	}
}
