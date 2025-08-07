package com.haloofwar.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InteractionPrompt {
	private float x, y;
	private int width, height;
	private boolean visible;
	private Texture texture;
	private float margin = 25;

	public InteractionPrompt(float x, float y, Texture texture) {
		this.visible = false;
		this.texture = texture;
		this.width = 16;
		this.height = 16;
		this.x = x;
		this.y = y;
	}

	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void show() {
		this.visible = true;
	}

	public void hide() {
		this.visible = false;
	}

	public void render(SpriteBatch batch) {
		if (!this.visible) {
			return;
		}

		System.out.println("Renderizando");
		batch.draw(this.texture, this.x, this.y + this.margin, this.width, this.height);
	}

	public void setVisibility(boolean value) {
		this.visible = value;
	}
}
