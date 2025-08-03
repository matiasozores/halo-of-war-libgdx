package com.haloofwar.entities;

import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.enumerators.entities.behavior.CollisionType;
import com.haloofwar.interfaces.Collidable;
import com.haloofwar.interfaces.CollisionVisitor;
import com.haloofwar.interfaces.Renderable;
import com.haloofwar.interfaces.StateHandler;

public abstract class Entity implements Collidable, Renderable {
	private String name;
	protected int width, height;
	protected float x, y;
	protected boolean active = true; 
	
	protected CollisionVisitor collisionBehavior;
	protected StateHandler state;
	
    public Entity(String name, int width, int height, StateHandler state) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.state = state;
    }
    
    public Entity(String name, float x, float y, int width, int height, StateHandler state) {
	    this(name, width, height, state);
	    this.x = x;
	    this.y = y;
    }
 
	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
	
    @Override
    public CollisionType getCollisionType() {
        return this.collisionBehavior.getCollisionType();
    }
    
    @Override
    public void accept(CollisionVisitor visitor, Collidable self) {
        visitor.visit(this, self);
    }
    
    @Override
    public CollisionVisitor getCollisionBehavior() {
    	if(this.collisionBehavior == null) {
			System.out.println("El behavior de colision no ha sido inicializado para " + this.name);
			return null;
		} 
    	
		return this.collisionBehavior;
	}

    public String getName() {
		return this.name;
	}
	
    public boolean isActive() {
    	return this.active;
    }
    
	public void setPosition(float x, float y) {
	    this.x = x;
	    this.y = y;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
}
