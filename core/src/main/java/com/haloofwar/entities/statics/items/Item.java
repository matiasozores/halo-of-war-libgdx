package com.haloofwar.entities.statics.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.dependences.collision.behaviors.ItemCollisionBehavior;
import com.haloofwar.entities.StaticEntity;
import com.haloofwar.entities.components.EntityStateHandler;

public abstract class Item extends StaticEntity {
	private final int DEFAULT_WIDTH = 16;
	private final int DEFAULT_HEIGHT = 16;

	private int visibleWidth;
	private int visibleHeight;
	
	private int stock = 1;

	// Dependencias
	private Texture texture;

	public Item(String name, Texture texture, float x, float y, int width, int height, EntityStateHandler state, ItemCollisionBehavior collisionBehavior) {
		super(name, x, y, width, height, state);
		
		this.texture = texture;
		this.visibleWidth = this.DEFAULT_WIDTH;
		this.visibleHeight = this.DEFAULT_HEIGHT;
		
		this.collisionBehavior = collisionBehavior;
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(this.texture, this.x, this.y, this.visibleWidth, this.visibleHeight);
	}
	
    public void affectStock(final int MOUNT) {
		this.stock += MOUNT;
	}
    
    public int getStock() {
		return this.stock;
	}
    
    public Texture getTexture() {
		return this.texture;
	}

}
