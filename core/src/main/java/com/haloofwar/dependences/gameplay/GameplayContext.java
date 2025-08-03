package com.haloofwar.dependences.gameplay;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.dependences.assets.TextureManager;
import com.haloofwar.dependences.collision.CollisionManager;
import com.haloofwar.dependences.input.InputManager;
import com.haloofwar.interfaces.Renderable;
import com.haloofwar.interfaces.Updatable;

public class GameplayContext implements Renderable, Updatable {
	private final EntityManager entities;
	private final BulletManager bullets;
	private final CollisionManager collisions;

	public GameplayContext(InputManager input, TextureManager texture) {
		this.entities = new EntityManager();
		this.collisions = new CollisionManager(); 
		this.bullets = new BulletManager(this.collisions, this.entities, texture);
	}	
	
	@Override
	public void render(SpriteBatch batch) {
		this.entities.render(batch);	
	}
	
	@Override
	public void update(float delta) {
		this.entities.update(delta);
		this.collisions.checkCollisions();
	}
	
	public EntityManager getEntities() {
		return this.entities;
	}
	
	public CollisionManager getCollisions() {
		return this.collisions;
	}
	
	public BulletManager getBullets() {
		return this.bullets;
	}
	
	public void dispose() {
		System.out.println("Ejecutando dispose de GameplayContext");
		this.entities.clear();
		this.collisions.clear();
		this.bullets.clear();
	}
}
