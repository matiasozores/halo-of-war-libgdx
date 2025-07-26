package com.haloofwar.utilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Image {
	private Texture texture;
	private int width, height;
	private float x, y;
	
	public Image(String path, int width, int height) {
		this.texture = new Texture(path);
		this.width = width;
		this.height = height;
	}
	
	public Image(String path) {
		this(path, 32, 32);
	}
	
	
	
	public void render(SpriteBatch batch) {
		batch.draw(this.texture, this.x, this.y, this.width, this.height);
	}
	
	public void render(SpriteBatch batch, float x, float y) {
		batch.draw(this.texture, x, y, this.width, this.height);
	}
	
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void dispose() {
		this.texture.dispose();
	}
}
