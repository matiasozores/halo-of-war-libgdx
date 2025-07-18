package com.haloofwar.components;

import com.haloofwar.input.InputManager;

public class MovementComponent {
	private float x = 0, y = 0;
	private int mapWidthLimit = 999999;  
	private int mapHeightLimit = 999999;
	
	public void update(InputManager inputManager, int width, int height, float movement) {
		if (inputManager.isMoveUp()) {
			this.y += movement;
		}
		if (inputManager.isMoveDown()) {
			this.y -= movement;
		}
		if (inputManager.isMoveLeft()) {
			this.x -= movement;
		}
		if (inputManager.isMoveRight()) {
			this.x += movement;
		}
		
		if (this.x < 0) {
			this.x = 0;
		}
		
		if (this.y < 0) {
			this.y = 0;
		}
		
		if (this.x + width > this.mapWidthLimit) {
			this.x = this.mapWidthLimit - width;
		}

		if (this.y + height > this.mapHeightLimit) {
			this.y = this.mapHeightLimit - height;
		}

	}
	
	public void setMapBounds(int mapWidth, int mapHeight) {
		this.mapWidthLimit = mapWidth;
		this.mapHeightLimit = mapHeight;
	}

	
	// Getters
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
	
	
}
