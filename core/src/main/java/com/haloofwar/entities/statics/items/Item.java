package com.haloofwar.entities.statics.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.dependences.collision.behaviors.ItemCollisionBehavior;
import com.haloofwar.entities.StaticEntity;
import com.haloofwar.entities.components.EntityStateHandler;
import com.haloofwar.interfaces.Updatable;
import com.haloofwar.ui.InteractionPrompt;

public abstract class Item extends StaticEntity implements Updatable{
	private final int DEFAULT_WIDTH = 16;
	private final int DEFAULT_HEIGHT = 16;

	private int visibleWidth;
	private int visibleHeight;
	
	private int stock = 1;

	private InteractionPrompt prompt;
	private boolean inRange = false;
	
	// Dependencias
	private Texture texture;

	
	public Item(
		String name,
		Texture texture,
		float x,
		float y,
		int width,
		int height,
		EntityStateHandler state,
		ItemCollisionBehavior collisionBehavior,
		InteractionPrompt prompt
	) {
		super(name, x, y, width, height, state);
		
		this.texture = texture;
		this.visibleWidth = this.DEFAULT_WIDTH;
		this.visibleHeight = this.DEFAULT_HEIGHT;
		
		this.collisionBehavior = collisionBehavior;
		this.prompt = prompt;
	}

	@Override
	public void update(float delta) {
		// Se pone en falso siempre para evitar que quede en true para siempre cuando esta en el rango y sale
		this.setInRange(false);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(this.texture, this.x, this.y, this.visibleWidth, this.visibleHeight);
		this.prompt.render(batch);
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
    
    public boolean isInRange() {
        return this.inRange;
    }
    
    public void setInRange(boolean value) {
        this.inRange = value;
        this.prompt.setVisibility(value);
    }

}
