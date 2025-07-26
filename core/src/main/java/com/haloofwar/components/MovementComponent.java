package com.haloofwar.components;

import com.haloofwar.dependences.InputManager;

public class MovementComponent {
	private float x = 0, y = 0;
	
	// Dependencias
	private InputManager inputManager;
	
	public MovementComponent(InputManager inputManager) {

		this.inputManager = inputManager;
	}
	
	public void update(float delta, float movementSpeed) {
		if (this.inputManager.isMoveUp()) {
			this.y += movementSpeed * delta;
		}
		if (this.inputManager.isMoveDown()) {
			this.y -= movementSpeed * delta;
		}
		if (this.inputManager.isMoveLeft()) {
			this.x -= movementSpeed * delta;
		}
		if (this.inputManager.isMoveRight()) {
			this.x += movementSpeed * delta;
		}
		
		if (this.x < 0) {
			this.x = 0;
		}
		
		if (this.y < 0) {
			this.y = 0;
		}
	}

	
	// Getters
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
}
