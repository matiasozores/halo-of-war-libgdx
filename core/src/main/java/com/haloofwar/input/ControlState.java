package com.haloofwar.input;

import com.badlogic.gdx.Gdx;
import com.haloofwar.utilities.Resources;

public class ControlState {
	private int width, height;
	private float x = 0, y = 0;
	private float movement = 1;
	
	private int mapWidthLimit = 999999;  // default grande
	private int mapHeightLimit = 999999;

	
	public ControlState(int width, int height, int movement) {
		this.movement = movement;
		this.width = width;
		this.height = height;
	}
	
	public void update(InputManager inputManager) {
		if (inputManager.isMoveUp()) {
			this.y += this.movement;
		}
		if (inputManager.isMoveDown()) {
			this.y -= this.movement;
		}
		if (inputManager.isMoveLeft()) {
			this.x -= this.movement;
		}
		if (inputManager.isMoveRight()) {
			this.x += this.movement;
		}
		
		if (this.x < 0) {
			this.x = 0;
		}
		
		if (this.y < 0) {
			this.y = 0;
		}
		
		if (this.x + this.width > this.mapWidthLimit) {
			this.x = this.mapWidthLimit - this.width;
		}

		if (this.y + this.height > this.mapHeightLimit) {
			this.y = this.mapHeightLimit - this.height;
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
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
}
