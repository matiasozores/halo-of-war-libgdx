package com.haloofwar.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.collision.Collidable;
import com.haloofwar.collision.CollisionManager;
import com.haloofwar.components.AnimationComponent;
import com.haloofwar.components.movement.MovementComponent;
import com.haloofwar.components.movement.MovementController;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.dependences.TextureManager;
import com.haloofwar.enumerators.CharacterType;
import com.haloofwar.enumerators.CollisionType;

public abstract class Entity implements Collidable{
	private final int DEFAULT_HEALTH = 100;
	private final int DEFAULT_VELOCITY = 150;
	
	private String name;
	private int health = this.DEFAULT_HEALTH;
	private float velocity = this.DEFAULT_VELOCITY;
	private boolean alive = true;
	private int width = 32, height = 32; 
	
	private MovementComponent movement;
	private AnimationComponent animation;
	private CollisionType type = CollisionType.ENTITY;
	
	public Entity(String name, CharacterType sprite, MovementController controller, GameWorldCamera camera, TextureManager textureManager, CollisionManager collisionManager) {
		this.name = name;
		this.movement = new MovementComponent(controller);
		this.animation = new AnimationComponent(sprite, textureManager);
		collisionManager.addCollidable(this);
	}
	
	public void update(float delta) {
		if(!this.alive) {
			return;
		} 
		
		this.movement.update(delta, this.velocity);
		// En Enemy
		this.animation.update(delta, movement.getController().getDirectionX(), movement.getController().getDirectionY());

	}
	
	public void render(SpriteBatch batch) {
		if(!this.alive) {	
			return;
		}
		
		this.animation.render(this.movement.getX(), this.movement.getY(), this.width, this.height, batch);
	}
	
	public void takeDamage(final int AMOUNT) {
	    this.health -= AMOUNT;
	    if (this.health <= 0) {
	        this.health = 0;
	        this.alive = false;
	    }
	}
	

	
	public float getX() {
		return this.movement.getX();
	}
	
	public float getY() {
		return this.movement.getY();
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public int getDEFAULT_HEALTH() {
		return this.DEFAULT_HEALTH;
	}
	
	public MovementComponent getMovement() {
		return this.movement;
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.getX(), this.getY(), this.width, this.height);
	}
	
	@Override
	public CollisionType getCollisionType() {
		return this.type;
	}
	
	public boolean isAlive() {
		return this.alive;
	}
	
	public void dispose(CollisionManager manager) {
		manager.removeCollidable(this);
	}
}
