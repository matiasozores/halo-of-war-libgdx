package com.haloofwar.utilities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Figure {
	private float x, y, width, height;

	public Figure(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Figure(float width, float height) {
		this(0, 0, width, height);
	}
	
	public void draw(ShapeRenderer shape) {
		shape.rect(this.x, this.y, this.width, this.height); 
	}
	
	public void draw(ShapeRenderer shape, float x, float y) {
		shape.rect(x, y, this.width, this.height); 
	}
	
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
}
