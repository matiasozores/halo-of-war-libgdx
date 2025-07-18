package com.haloofwar.entities.characters;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.haloofwar.components.AnimationComponent;
import com.haloofwar.components.MovementComponent;
import com.haloofwar.enumerators.EntityType;
import com.haloofwar.input.InputManager;
import com.haloofwar.ui.Crosshair;

public abstract class Player {
	private final int DEFAULT_HEALTH = 100;
	private final int DEFAULT_VELOCITY = 1;
	
	private String name;
	private int health = this.DEFAULT_HEALTH;
	private float velocity = this.DEFAULT_VELOCITY;
	private boolean alive = true;
	
	private int width = 32, height = 32; 
	
	private MovementComponent movement;
	private AnimationComponent animation;
	private Crosshair crosshair = new Crosshair();
	
	
	public Player(String name, EntityType sprite) {
		this.name = name;
		this.animation = new AnimationComponent(sprite);
		this.movement = new MovementComponent();
	}
	
	public void update(InputManager inputManager) {
		if(!alive) {
			return;
		} 
		
		this.movement.update(inputManager, this.width, this.height, this.velocity);
		this.animation.update(inputManager);
		this.crosshair.update();
	}
	
	public void render(OrthographicCamera camera) {
		if(!alive) {
			return;
		}
		
		this.animation.render(this.movement.getX(), this.movement.getY(), this.width, this.height, camera);
		this.crosshair.render(camera);
	}
	
	public float getX() {
		return this.movement.getX();
	}
	
	public float getY() {
		return this.movement.getY();
	}
	
	public String getName() {
		return name;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getDEFAULT_HEALTH() {
		return DEFAULT_HEALTH;
	}
	
	public MovementComponent getMovement() {
		return this.movement;
	}	
}
