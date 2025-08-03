package com.haloofwar.components.movement;

import com.haloofwar.interfaces.MovementController;

public class MovementComponent {
	private float x = 500, y = 100; 
	private float lastX = x, lastY = y;

	private final MovementController controller;

	public MovementComponent(MovementController controller) {
		this.controller = controller;
	}

	public MovementComponent(MovementController controller, float x, float y) {
		this.controller = controller;
		this.x = x;
		this.y = y;
		this.lastX = x;
		this.lastY = y;
	}

	public void update(float delta, float speed) {
		this.lastX = this.x;
		this.lastY = this.y;

		this.x += controller.getDirectionX() * speed * delta;
		this.y += controller.getDirectionY() * speed * delta;
	}

	public float getX() { 
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
	
	public float getLastX() {
	    return this.lastX;
	}

	public float getLastY() {
	    return this.lastY;
	}

	public float getDirectionX() { 
		return this.controller.getDirectionX();
	}
	
	public float getDirectionY() { 
		return this.controller.getDirectionY();
	}

	public void setX(float x) { 
		this.x = x;
	}
	
	public void setY(float y) { 
		this.y = y;
	}

	public MovementController getController() {
		return this.controller;
	}
}

