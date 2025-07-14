package com.haloofwar.entities.characters;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.haloofwar.enumerators.Sprite;
import com.haloofwar.input.ControlState;
import com.haloofwar.input.InputManager;
import com.haloofwar.utilities.MyAnimation;

public abstract class Player {
	private final int DEFAULT_HEALTH = 100;
	private final int DEFAULT_VELOCITY = 1;
	
	private String name;
	private int health = this.DEFAULT_HEALTH;
	private int velocity = this.DEFAULT_VELOCITY;
	private boolean alive = true;
	private int[] color;
	
	private ControlState controlState = new ControlState(32, 32, this.DEFAULT_VELOCITY);
	private MyAnimation animation;
	
	public Player(String name, int[] color, Sprite sprite) {
		this.name = name;
		this.color = color;
		this.animation = new MyAnimation(sprite);
	}
	
	public void update(InputManager inputManager) {
		if(!alive) {
			return;
		} 
		
		this.controlState.update(inputManager);
		this.animation.update(inputManager);
	}
	
	public void render(ShapeRenderer shapeRenderer, InputManager inputManager) {
		if(!alive) {
			return;
		}
		
		shapeRenderer.setColor(this.color[0], this.color[1], this.color[2], 1);
		shapeRenderer.rect(this.controlState.getX(), this.controlState.getY(), this.controlState.getWidth(), this.controlState.getHeight());
		this.animation.render(this.controlState.getX(), this.controlState.getY());
		
		
		shapeRenderer.setColor(this.color[0], this.color[1], this.color[2], 1);
		shapeRenderer.rect(inputManager.getMouseX(), inputManager.getMouseY(), 10, 10);
	
		shapeRenderer.setColor(1, 1, 1, 1);
	}
	
	public float getX() {
		return this.controlState.getX();
	}
	
	public float getY() {
		return this.controlState.getY();
	}
	
	public ControlState getControlState() {
		return this.controlState;
	}
	
	
}
