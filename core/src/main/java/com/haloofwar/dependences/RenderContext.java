package com.haloofwar.dependences;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class RenderContext {
	private final SpriteBatch batch;
	private final ShapeRenderer shape;
	private final FontManager font;
	
	public RenderContext() {
		this.batch = new SpriteBatch();
		this.shape = new ShapeRenderer();
		this.font = new FontManager();
	}
	
	public SpriteBatch getBatch() {
		return this.batch;
	}
	
	public ShapeRenderer getShape() {
		return this.shape;
	}
	
	public FontManager getFont() {
		return this.font;
	}
	
	public void dispose() {
		this.batch.dispose();
		this.shape.dispose();
		this.font.dispose();
	}
}
