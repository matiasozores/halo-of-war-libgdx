package com.haloofwar.components.movement;

public class MovementComponent {
	private float x = 500, y= 100;
	private final MovementController controller;

	public MovementComponent(MovementController controller) {
		this.controller = controller;
	}
	
	public MovementComponent(MovementController controller, float x, float y) {
		this.controller = controller;
		this.x = x;
		this.y = y;
	}

	public void update(float delta, float speed) {
		this.x += this.controller.getDirectionX() * speed * delta;
		this.y += this.controller.getDirectionY() * speed * delta;

		if (x < 0) {
			x = 0;
		}
			
		if (y < 0) {
			y = 0;
		}
	}

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
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
