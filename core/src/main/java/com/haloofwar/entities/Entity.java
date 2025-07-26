package com.haloofwar.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.collision.Collidable;
import com.haloofwar.collision.CollisionManager;
import com.haloofwar.components.AnimationComponent;
import com.haloofwar.components.MovementComponent;
import com.haloofwar.dependences.InputManager;
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
	
	private CollisionType type = CollisionType.PLAYER;
	
	
	public Entity(String name, CharacterType sprite, InputManager inputManager, GameWorldCamera camera, TextureManager textureManager, CollisionManager collisionManager) {
		this.name = name;
		this.movement = new MovementComponent(inputManager);
		this.animation = new AnimationComponent(sprite, inputManager, textureManager);
		collisionManager.addCollidable(this);
	}
	
	public void update(float delta) {
		if(!this.alive) {
			return;
		} 
		
		this.movement.update(delta, this.velocity);
		this.animation.update(delta);
	}
	
	public void render(SpriteBatch batch) {
		if(!this.alive) {	
			return;
		}

		this.animation.render(this.movement.getX(), this.movement.getY(), this.width, this.height, batch);
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
}
