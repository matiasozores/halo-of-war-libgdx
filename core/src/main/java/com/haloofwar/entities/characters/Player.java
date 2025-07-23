package com.haloofwar.entities.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.components.AnimationComponent;
import com.haloofwar.components.MovementComponent;
import com.haloofwar.enumerators.CharacterType;
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
	
	private Crosshair crosshair;
	
	public Player(String name, CharacterType sprite) {
		this.name = name;
		this.animation = new AnimationComponent(sprite);
		this.movement = new MovementComponent();
		this.crosshair = new Crosshair(sprite.getCrosshairPath());
	}
	
	public void update(GameWorldCamera camera, InputManager inputManager) {
		if(!alive) {
			return;
		} 
		
		this.movement.update(inputManager, this.width, this.height, this.velocity);
		this.animation.update(inputManager);
		this.crosshair.update(camera);
	}
	
	public void render(SpriteBatch batch) {
		if(!alive) {	
			return;
		}

		this.animation.render(this.movement.getX(), this.movement.getY(), this.width, this.height, batch);
		this.crosshair.render(batch);
	}
	
	public void setMapBounds(int mapWidth, int mapHeight) {
		this.movement.setMapBounds(mapWidth, mapHeight);
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
}
