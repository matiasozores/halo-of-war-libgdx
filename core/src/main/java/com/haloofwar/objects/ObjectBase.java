package com.haloofwar.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.dependences.assets.TextureManager;
import com.haloofwar.dependences.collision.Collidable;
import com.haloofwar.dependences.collision.CollisionManager;
import com.haloofwar.enumerators.CollisionType;
import com.haloofwar.enumerators.ObjectType;

public abstract class ObjectBase implements Collidable{
	private final int DEFAULT_WIDTH = 16;
	private final int DEAFULT_HEIGHT = 16;
	
	private String name;
	private int width = this.DEFAULT_WIDTH;
	private int height = this.DEAFULT_HEIGHT;
	private int x = 100, y = 100;
	private Texture texture;
	
	private CollisionType type = CollisionType.OBJECT;
	
	public ObjectBase(String name, ObjectType type, TextureManager textureManager, CollisionManager collisionManager) {
		this.name = name;
		this.texture = textureManager.get(type);
		collisionManager.addCollidable(this);
	}
	
	public void update() {
		
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(this.texture, this.x, this.y, this.width, this.height);
	}
	
	public String getName() {
		return this.name;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
	
	public CollisionType getCollisionType() {
		return this.type;
	}

}
